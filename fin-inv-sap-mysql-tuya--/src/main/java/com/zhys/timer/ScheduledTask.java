package com.zhys.timer;

import com.alibaba.fastjson.JSONObject;
import com.sap.conn.jco.*;
import com.zhys.exception.BusinessException;
import com.zhys.po.InvoiceHead;
import com.zhys.po.InvoiceSplitLine;
import com.zhys.po.TaxCatcode;
import com.zhys.pool.JCoDestinationPool;
import com.zhys.redis.RedisUtils;
import com.zhys.result.ResultCode;
import com.zhys.service.InvoiceHeadService;
import com.zhys.service.impl.InvoiceHeadServiceImpl;
import com.zhys.util.PostUtil;
import com.zhys.util.SQLManager;
import com.zhys.util.ThreadPoolManager;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ScheduledTask {
    @Autowired
    private JCoDestinationPool pool;

    @Autowired
    private RedisUtils redis;

    @Autowired
    private SQLManager manager;

    @Autowired
    private ThreadPoolManager threadPoolManager;

    @Autowired
    private InvoiceHeadService invoiceHeadService;



    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



    private boolean validateServiceStart(String key){
        Object v = redis.get(key);
        if(v!=null){
            //log.info("redis获取到值："+v);
            String val = ""+v;
            if(val.contains("1")){
                //log.info("redis成功");
                return true;
            }
        }
        //log.info("redis失败");
        return false;
    }



    private  JCoFunction getWaitTaxCatcodeFromSap(JCoDestination destination)throws JCoException{
        JCoFunction function = destination.getRepository().getFunction("ZFI_JSI03");//从对象仓库中获取 RFM 函数

        if (function == null)

            throw new RuntimeException("ZFI_JSI03 not found in SAP.");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String IV_FKDAT = sdf.format(new Date());
        log.info("获取物料日期："+IV_FKDAT);
        //1.单独的参数，不在表结构下
        function.getImportParameterList().setValue("IV_FKDAT", IV_FKDAT);// 参数

        try {
            function.execute(destination);
        } catch (AbapException e) {
            System.out.println(e.toString());
            log.info(e.toString());
            return null;
        }

        String EX_TYPE = function.getExportParameterList()
                .getString("EX_TYPE");
        System.out.println("获取物料EX_TYPE "

                + EX_TYPE + ":\n");

        String EX_MESSAGE = function.getExportParameterList()

                .getString("EX_MESSAGE");
        System.out.println("获取物料EX_MESSAGE "

                + EX_MESSAGE + ":\n");
log.info("获取物料EX_MESSAGE "

        + EX_MESSAGE + ":\n");
        return function;
    }

    private  void setWaitTaxCatcodeToSap(JCoDestination destination,List<TaxCatcode> taxCatcodes)throws JCoException{
        if(taxCatcodes!=null&&taxCatcodes.size()>0){
            JCoFunction function1 = destination.getRepository().getFunction(

                    "ZFI_JSI04");//从对象仓库中获取 RFM 函数

            if (function1 == null)

                throw new RuntimeException(

                        "ZFI_JSI04 not found in SAP.");

            JCoTable options = function1.getTableParameterList().getTable("IT_TABLE");
            for(TaxCatcode taxCatcode:taxCatcodes){
                options.appendRow();
                options.setValue("MATNR", taxCatcode.getMATNR());
                options.setValue("ZMATNR_TAX", taxCatcode.getZMATNR_TAX());
                options.setValue("TAX_RATE", taxCatcode.getTAX_RATE());
                options.setValue("MAKTX", taxCatcode.getMAKTX());
                options.setValue("MANDT", taxCatcode.getMANDT());
                options.setValue("ZDATE", taxCatcode.getZDATE());
                options.setValue("FLAG", "");
            }



            try {

                function1.execute(destination);

            } catch (AbapException e) {

                System.out.println(e.toString());

                return ;

            }

            String EX_TYPE = function1.getExportParameterList()

                    .getString("EX_TYPE");
            System.out.println("回传物料EX_TYPE "

                    + EX_TYPE + ":\n");

            String EX_MESSAGE = function1.getExportParameterList()

                    .getString("EX_MESSAGE");
            System.out.println("回传物料EX_MESSAGE "

                    + EX_MESSAGE + ":\n");
            log.info("回传物料EX_MESSAGE "

                    + EX_MESSAGE + ":\n");
        }

    }





    
    
    
    
    //待开票信息（从sap中获取维护），没隔6s执行一次
     @Scheduled(fixedRate = 1000*6)
     @Transactional
    public void dkpTimer() throws JCoException {
         //检验   PC端开票服务是否启动(pc端开票服务启动，会存kp值)
         boolean b = validateServiceStart("kp");
         if(b){
             //如果PC端启动开票了，执行下面的逻辑
             threadPoolManager.threadPool.execute(new Runnable() {
                 @Override
                 public void run() {
//                	     调用发票头服务  --撤销
                       invoiceHeadService.revoke("1000", "1","1");
                 }
             });


         }

    }

    //待作废信息
   @Scheduled(fixedRate = 1000*10)
   @Transactional
    public void dzfTimer() throws JCoException {

        boolean b = validateServiceStart("zf");
        if(b){

            threadPoolManager.threadPool.execute(new Runnable() {
                @Override
                public void run() {
                        invoiceHeadService.revoke("1000", "3","1");
                }
            });

        }

    }




    //回传开票信息
    @Scheduled(fixedRate = 5000)
    @Transactional
    public void hcfp() throws JCoException{
        InvoiceHead invoiceHead = new InvoiceHead();
        invoiceHead.setIssync("1");
        List<InvoiceHead> list =  (List<InvoiceHead>)manager.list("invoice_head.fphc", invoiceHead);
        if(list!=null&&list.size()>0){
            invoiceHead = list.get(0);
            /**
             * 修改发票表头表体信息
             */
            JCoDestination destination= pool.getJCoDestination();
            JCoFunction function4 = destination.getRepository().getFunction(

                    "ZFI_JSI02");//从对象仓库中获取 RFM 函数

            if (function4 == null)

                throw new RuntimeException(

                        "ZFI_JSI02 not found in SAP.");

            JCoTable options = function4.getTableParameterList().getTable("IT_TABLE_HE");
            // modification date >= 2012.01.01 and <= 2015.12.31
            options.appendRow();
            options.setValue("MANDT", invoiceHead.getMandt());
            options.setValue("DOC_NUM", invoiceHead.getDocNum());
            options.setValue("DOC_DATE", invoiceHead.getDocDate());
            options.setValue("DOC_STATUS", invoiceHead.getDocStatus());
            options.setValue("ORG_ID", invoiceHead.getOrgId());
            options.setValue("ORG_NAME", invoiceHead.getOrgName());
            options.setValue("ORG_TAXCODE", invoiceHead.getOrgTaxcode());
            options.setValue("ORG_MACHINE", invoiceHead.getOrgMachine());
            options.setValue("ORG_ADDRESS", invoiceHead.getOrgAddress());
            options.setValue("ORG_TELEPHONE", invoiceHead.getOrgTelephone());
            options.setValue("ORG_BANKNAME", invoiceHead.getOrgBankname());
            options.setValue("ORG_BANKACCOUNT", invoiceHead.getOrgBankaccount());
            if(invoiceHead.getOrgTaxexceed()!=null){
                options.setValue("ORG_TAXEXCEED", invoiceHead.getOrgTaxexceed());
            }
            options.setValue("ORG_CONTROLTAX", invoiceHead.getOrgControltax());
            options.setValue("CUST_ID_AR", invoiceHead.getCustIdAr());
            options.setValue("CUST_NAME_AR", invoiceHead.getCustNameAr());
            options.setValue("CUST_ID_BILL", invoiceHead.getCustIdBill());
            options.setValue("CUST_NAME_BILL", invoiceHead.getCustNameBill());
            options.setValue("CUST_NAME", invoiceHead.getCustName());
            options.setValue("CUST_TAXCODE", invoiceHead.getCustTaxcode());
            options.setValue("CUST_ADDRESS", invoiceHead.getCustAddress());
            options.setValue("CUST_TELEPHONE", invoiceHead.getCustTelephone());
            options.setValue("CUST_BANKNAME", invoiceHead.getCustBankname());
            options.setValue("CUST_BANKACCOUNT", invoiceHead.getCustBankaccount());
            options.setValue("CUST_EMAIL", invoiceHead.getCustEmail());
            options.setValue("CUST_MOBILE", invoiceHead.getCustMobile());

            options.setValue("INVOICE_TYPE", invoiceHead.getInvoiceType());
            options.setValue("INVOICE_TYPES", invoiceHead.getInvoiceTypes());
            options.setValue("INVOICE_BASE", invoiceHead.getInvoiceBase());
            options.setValue("INVOICE_WAY", invoiceHead.getInvoiceWay());
            if(invoiceHead.getMergeAmt()!=null){
                options.setValue("MERGE_AMT", invoiceHead.getMergeAmt());
            }
            if(invoiceHead.getMergeQty()!=null){
                options.setValue("MERGE_QTY", invoiceHead.getMergeQty());
            }

            options.setValue("TAX_RATE", invoiceHead.getTaxRate());
            options.setValue("DISCOUNT_TYPE", invoiceHead.getDiscountType());
            if(invoiceHead.getDiscountRate()!=null){
                options.setValue("DISCOUNT_RATE", invoiceHead.getDiscountRate());
            }

            options.setValue("MERGE_GIFT", invoiceHead.getMergeGift());
            options.setValue("INVOICE_LIST", invoiceHead.getInvoiceList());
            options.setValue("INVOICE_RED", invoiceHead.getInvoiceRed());
            options.setValue("MERGEF_TYPE", invoiceHead.getMergefType());
            options.setValue("MERGES_TYPE", invoiceHead.getMergesType());
            options.setValue("GOLDTAX_CODE", invoiceHead.getGoldtaxCode());
            options.setValue("GOLDTAX_NUM", invoiceHead.getGoldtaxNum());

            options.setValue("USER_ID", invoiceHead.getUserId());
            options.setValue("USER_NAME", invoiceHead.getUserName());
            options.setValue("CHECK_NAME", invoiceHead.getCheckName());
            options.setValue("PAYEE_NAME", invoiceHead.getPayeeName());

            options.setValue("BILL_DATE", invoiceHead.getBillDate());
            options.setValue("CANCEL_DATE", invoiceHead.getCancelDate());
            options.setValue("BILL_GDATE", invoiceHead.getBillGdate());
            options.setValue("CANCEL_GDATE", invoiceHead.getCancelGdate());
            options.setValue("INVOICE_RED_REQM", invoiceHead.getInvoiceRedReqm());
            options.setValue("INVOICE_RED_XXBM", invoiceHead.getInvoiceRedXxbm());
            options.setValue("INVOICE_RED_FPDM", invoiceHead.getInvoiceRedFpdm());
            options.setValue("INVOICE_RED_FPHM", invoiceHead.getInvoiceRedFphm());

            options.setValue("BILL_REMARK", invoiceHead.getBillRemark());

            if(invoiceHead.getZamountHswc()!=null){
                options.setValue("ZAMOUNT_HSWC", invoiceHead.getZamountHswc());
            }

            if(invoiceHead.getZamountWswc()!=null){
                options.setValue("ZAMOUNT_WSWC", invoiceHead.getZamountWswc());
            }

            if(invoiceHead.getZamountSewc()!=null){
                options.setValue("ZAMOUNT_SEWC", invoiceHead.getZamountSewc());
            }


            options.setValue("CREATED_BY", invoiceHead.getCreatedBy());
            options.setValue("CREATION_DATE", invoiceHead.getCreationDate());
            options.setValue("LAST_UPDATED_BY", invoiceHead.getLastUpdatedBy());
            options.setValue("LAST_UPDATED_DAT", invoiceHead.getLastUpdatedDat());


            //options.setValue("ATTRIBUTE1", invoiceHead.getAttribute1());
            options.setValue("ATTRIBUTF1", invoiceHead.getAttributf1());
            //options.setValue("ATTRIBUTE2", invoiceHead.getAttribute2());
            //options.setValue("ATTRIBUTE3", invoiceHead.getAttribute3());

            options.setValue("CUST_PROV_EX", invoiceHead.getCustProvEx());
            options.setValue("CUST_CITY", invoiceHead.getCustCity());
            options.setValue("CUST_DISTRICT", invoiceHead.getCustDistrict());
            options.setValue("CUST_ADDR_EX", invoiceHead.getCustAddrEx());
            options.setValue("EX_NAME", invoiceHead.getExName());
            options.setValue("EX_TELEPHONE", invoiceHead.getExTelephone());

            JCoTable options1 = function4.getTableParameterList().getTable("IT_TABLE_GR");
            for(InvoiceSplitLine invoiceSplitLine:invoiceHead.getInvoiceSplitLines()) {
                options1.appendRow();
                options1.setValue("MANDT", invoiceSplitLine.getMandt());
                options1.setValue("DOC_NUM", invoiceSplitLine.getDocNum());
                options1.setValue("DOC_LINE", invoiceSplitLine.getDocLine());
                options1.setValue("GROUP_NUM", invoiceSplitLine.getGroupNum());
                options1.setValue("GROUP_STATUS", invoiceSplitLine.getGroupStatus());
                options1.setValue("GOLDTAX_CODE", invoiceSplitLine.getGoldtaxCode());
                options1.setValue("GOLDTAX_NUM", invoiceSplitLine.getGoldtaxNum());

                options1.setValue("ITEM_NAME", invoiceSplitLine.getItemName());
                options1.setValue("ITEM_SPEC", invoiceSplitLine.getItemSpec());
                options1.setValue("UNIT_NAME", invoiceSplitLine.getUnitName());

                options1.setValue("TAX_CATECODE", invoiceSplitLine.getTaxCatecode());
                if(invoiceSplitLine.getQuantity()!=null){
                    options1.setValue("QUANTITY", invoiceSplitLine.getQuantity());
                }
                if(invoiceSplitLine.getTaxRate()!=null){
                    options1.setValue("TAX_RATE", invoiceSplitLine.getTaxRate());
                }
                options1.setValue("GIFT_FLAG", invoiceSplitLine.getGiftFlag());

                if(invoiceSplitLine.getZamountHsj()!=null){
                    options1.setValue("ZAMOUNT_HSJ", invoiceSplitLine.getZamountHsj());
                }
                if(invoiceSplitLine.getZamountWsj()!=null){
                    options1.setValue("ZAMOUNT_WSJ", invoiceSplitLine.getZamountWsj());
                }
                if(invoiceSplitLine.getZamountSej()!=null){
                    options1.setValue("ZAMOUNT_SEJ", invoiceSplitLine.getZamountSej());
                }
                if(invoiceSplitLine.getZamountHzhs()!=null){
                    options1.setValue("ZAMOUNT_HZHS", invoiceSplitLine.getZamountHzhs());
                }
                if(invoiceSplitLine.getZamountHzws()!=null){
                    options1.setValue("ZAMOUNT_HZWS", invoiceSplitLine.getZamountHzws());
                }
                if(invoiceSplitLine.getZamountHzse()!=null){
                    options1.setValue("ZAMOUNT_HZSE", invoiceSplitLine.getZamountHzse());
                }
                if(invoiceSplitLine.getZamountHsy()!=null){
                    options1.setValue("ZAMOUNT_HSY", invoiceSplitLine.getZamountHsy());
                }
                if(invoiceSplitLine.getZamountWsy()!=null){
                    options1.setValue("ZAMOUNT_WSY", invoiceSplitLine.getZamountWsy());
                }
                if(invoiceSplitLine.getZamountSey()!=null){
                    options1.setValue("ZAMOUNT_SEY", invoiceSplitLine.getZamountSey());
                }
                if(invoiceSplitLine.getZpriceHsj()!=null){
                    options1.setValue("ZPRICE_HSJ", invoiceSplitLine.getZpriceHsj());
                }
                if(invoiceSplitLine.getZpriceWsj()!=null){
                    options1.setValue("ZPRICE_WSJ", invoiceSplitLine.getZpriceWsj());
                }
                if(invoiceSplitLine.getZpriceHsy()!=null){
                    options1.setValue("ZPRICE_HSY", invoiceSplitLine.getZpriceHsy());
                }
                if(invoiceSplitLine.getZpriceWsy()!=null){
                    options1.setValue("ZPRICE_WSY", invoiceSplitLine.getZpriceWsy());
                }
                options1.setValue("BILL_DATE", invoiceSplitLine.getBillDate());
                options1.setValue("CANCEL_DATE", invoiceSplitLine.getCancelDate());
                options1.setValue("BILL_GDATE", invoiceSplitLine.getBillGdate());
                options1.setValue("CANCEL_GDATE", invoiceSplitLine.getCancelGdate());
                options1.setValue("GROUP_COPYFROM", invoiceSplitLine.getGroupCopyfrom());



                //options1.setValue("ATTRIBUTE1", invoiceSplitLine.getAttribute1());
                options1.setValue("ATTRIBUTF1", invoiceSplitLine.getAttributf1());
                //options1.setValue("ATTRIBUTE2", invoiceSplitLine.getAttribute2());
                //options1.setValue("ATTRIBUTE3", invoiceSplitLine.getAttribute3());

                options1.setValue("EXNUM", invoiceSplitLine.getExnum());

            }
            try {

                function4.execute(destination);

            } catch (AbapException e) {

                System.out.println(e.toString());

                return ;

            }

            String EX_TYPE = function4.getExportParameterList()

                    .getString("EX_TYPE");
            System.out.println("发票回传EX_TYPE "

                    + EX_TYPE + ":\n");

            String EX_MESSAGE = function4.getExportParameterList()

                    .getString("EX_MESSAGE");
            log.info(invoiceHead.getDocNum()+"发票回传EX_MESSAGE "

                    + EX_MESSAGE + ":\n");
            if(EX_TYPE.equals("S")){
                //更新发票同步状态
                invoiceHead.setIssync("2");
                manager.update("invoice_head.changeIssyncById",invoiceHead);
            }
        }


    }



    // token
   // @Scheduled(fixedRate = 1000*60*30)
    public void wechatToken() {
        String accessToken = PostUtil.post("client_id=82099521&&client_secret=8643505E04A24EDA&&grant_type=client_credentials", "https://open.jss.com.cn/accessToken");
        JSONObject jsonObject =JSONObject.parseObject(accessToken);
        if(jsonObject!=null&& !StringUtils.isEmpty(jsonObject.getString("access_token"))){
            redis.set("tz-accessToken", jsonObject.getString("access_token"));
        }

    }


    //每晚8点发送邮件 短信
  //  @Scheduled(cron = "0 0 20 * * ? ")
    //@Transactional
    public void waitSend(){
        invoiceHeadService.waitSend();
    }

    //每晚8点30发送邮寄短信
 //   @Scheduled(cron = "0 0 19 * * ? ")
    //@Transactional
    public void exWaitSend(){
        invoiceHeadService.exWaitSend();
    }

    //处理表头发票代码未拼接问题
//    @Scheduled(cron = "0 20 18 * * ? ")
    public void fphm(){
       List<InvoiceHead> list = (List<InvoiceHead>) manager.list("invoice_head.queryList",new InvoiceHead());
       for(InvoiceHead h:list){
          String hm =  getHM(h.getDocNum());
          if(StringUtils.isNotEmpty(hm)&&hm.length()>3){
              InvoiceHead head = new InvoiceHead();
              head.setIssync("1");
              head.setGoldtaxNum(hm);
              head.setDocNum(h.getDocNum());
              manager.update("invoice_head.ycsj",head);
          }
       }
    }


    private String getHM(String doc_num)
    {
        String hm = "";
        InvoiceSplitLine splitLine = new InvoiceSplitLine();
        splitLine.setDocNum(doc_num);
        List<InvoiceSplitLine> splitLines =  (List<InvoiceSplitLine> )manager.list("invoice_split_line.getHM",splitLine);
        if (splitLines!=null&&splitLines.size()>1)
        {


            long fphm0 = 0;//参照 前一张发票号码
            int i=0;
            for (InvoiceSplitLine line:splitLines)
            {


                log.info("goldtax_num:" + line.getGoldtaxNum());
                if (line.getGoldtaxNum()!=null&&line.getGoldtaxNum().trim().length()==0) {
                    continue;
                }
                long fphm1 = Long.parseLong(line.getGoldtaxNum());
                String sfphm1 = "" + fphm1;
                if (i == 0)
                {
                    hm = formatHm(sfphm1);
                    fphm0 = fphm1;
                }
                else
                {
                    if (fphm1 - 1 == fphm0)
                    {
                        if (hm.contains("-")&&"-".equals(hm.substring(hm.length() - 3,hm.length()-2)))
                        {//存在
                            hm = hm.substring(0, hm.length() - 2) + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
                        }
                        else
                        {
                            hm = formatHm(hm) + "-" + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
                        }
                        fphm0 = fphm1;

                    } else if (fphm1== fphm0) {

                    }
                    else
                    {
                        fphm0 = fphm1;
                        hm = hm + " " + formatHm(fphm0 + "");
                    }



                }

                i++;


            }

        }
        return hm;
    }





    private static  String formatHm(String hm)
    {

        String fphm = "";
        if (hm.length() == 7)
        {
            fphm = "0" + hm;
        }
        else if (hm.length() == 6)
        {
            fphm = "00" + hm;
        }
        else if (hm.length() == 5)
        {
            fphm = "000" + hm;
        }
        else if (hm.length() == 4)
        {
            fphm = "0000" + hm;
        }
        else if (hm.length() == 3)
        {
            fphm = "00000" + hm;
        }
        else if (hm.length() == 2)
        {
            fphm = "000000" + hm;
        }
        else if (hm.length() == 1)
        {
            fphm = "0000000" + hm;
        }
        else
        {
            fphm = hm;
        }

        return fphm;
    }



    //查询表头发票代码组装异常的情况
//    @Scheduled(fixedRate = 1000*60*60*24)
    public void hm_error(){
        log.info("开始过滤表头发票号码组装异常数据=================");
        List<InvoiceHead> list = (List<InvoiceHead>) manager.list("invoice_head.queryErr",new InvoiceHead());
        if(list!=null){
            log.info("总处理数据量：{}",list.size());
        }
        for(InvoiceHead h:list){
            String hm =  getHM_new(h.getDocNum());
            if(StringUtils.isNotEmpty(hm)&&!hm.equals(h.getGoldtaxNum())){
                log.info("单据号：{},old_hm:{},now_hm:{}",h.getDocNum(),h.getGoldtaxNum()==null?"":h.getGoldtaxNum(),hm);
                InvoiceHead head = new InvoiceHead();
                head.setIssync("1");
                head.setGoldtaxNum(hm);
                head.setDocNum(h.getDocNum());
                manager.update("invoice_head.ycsj",head);
            }
        }

        log.info("过滤表头发票号码组装完成=================");
    }

    private String getHM_new(String doc_num)
    {
        String hm = "";
        try {

            InvoiceSplitLine splitLine = new InvoiceSplitLine();
            splitLine.setDocNum(doc_num);
            List<InvoiceSplitLine> splitLines =  (List<InvoiceSplitLine> )manager.list("invoice_split_line.getHM",splitLine);
            if (splitLines!=null&&splitLines.size()>0)
            {


                long fphm0 = 0;//参照 前一张发票号码
                int i=0;
                for (InvoiceSplitLine line:splitLines)
                {


                    log.info("goldtax_num:" + line.getGoldtaxNum());
                    if (line.getGoldtaxNum()!=null&&line.getGoldtaxNum().trim().length()==0) {
                        continue;
                    }
                    long fphm1 = Long.parseLong(line.getGoldtaxNum());
                    String sfphm1 = "" + fphm1;
                    if (i == 0)
                    {
                        hm = formatHm(sfphm1);
                        fphm0 = fphm1;
                    }
                    else
                    {
                        if (fphm1 - 1 == fphm0)
                        {
                            if (hm.contains("-")&&"-".equals(hm.substring(hm.length() - 3,hm.length()-2)))
                            {//存在
                                hm = hm.substring(0, hm.length() - 2) + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
                            }
                            else
                            {
                                hm = formatHm(hm) + "-" + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
                            }
                            fphm0 = fphm1;

                        } else if (fphm1== fphm0) {

                        }
                        else
                        {
                            fphm0 = fphm1;
                            hm = hm + " " + formatHm(fphm0 + "");
                        }



                    }

                    i++;


                }

            }
            log.info("当前doc_num:{}发票号码组装完成,fphm:{}",doc_num,hm);
            return hm;
        }catch (Exception e){
            e.printStackTrace();
            log.error("组装发票号码异常,单据号：{}",doc_num);
            log.info("组装发票号码异常,单据号：{}",doc_num);
        }

        return "";

    }

    public static void main(String[] args) {


                String arr[] = {"06980562","06980563","06980564","06980566","06980568","06980569","06980570","06980571","06980572",
                        "06980573","06980575","06980580","06980581","06980582","06980583","06980584"
                };
        String hm = "";
        long fphm0 = 0;//参照 前一张发票号码
        int i=0;
        for (String line:arr)
        {


            log.info("goldtax_num:" + line);
            if (line!=null&&line.trim().length()==0) {
                continue;
            }
            long fphm1 = Long.parseLong(line);
            String sfphm1 = "" + fphm1;
            if (i == 0)
            {
                hm = formatHm(sfphm1);
                fphm0 = fphm1;
            }
            else
            {
                if (fphm1 - 1 == fphm0)
                {
                    if (hm.contains("-")&&"-".equals(hm.substring(hm.length() - 3,hm.length()-2)))
                    {//存在
                        hm = hm.substring(0, hm.length() - 2) + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
                    }
                    else
                    {
                        hm = formatHm(hm) + "-" + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
                    }
                    fphm0 = fphm1;

                } else if (fphm1== fphm0) {

                }
                else
                {
                    fphm0 = fphm1;
                    hm = hm + " " + formatHm(fphm0 + "");
                }



            }

            i++;


        }
        System.out.println(hm);
    }



}
