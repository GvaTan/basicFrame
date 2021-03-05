package com.zhys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.lycheeframework.core.cmp.kit.EasyPage;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.model.IPO;
import com.sap.conn.jco.*;
import com.zhys.base.BaseApiService;
import com.zhys.exception.BusinessException;
import com.zhys.po.*;
import com.zhys.pool.JCoDestinationPool;
import com.zhys.redis.RedisUtils;
import com.zhys.result.ResultCode;
import com.zhys.service.InvoiceHeadService;
import com.zhys.util.*;
import com.zhys.ws.ApiResult;
import com.zhys.ws.ISmsService;
import com.zhys.ws.SmsServiceImplService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InvoiceHeadServiceImpl extends BaseApiService implements InvoiceHeadService {
  private static final Logger log = LoggerFactory.getLogger(InvoiceHeadServiceImpl.class);
  
  @Autowired
  private JCoDestinationPool pool;
  
  @Autowired
  private SQLManager manager;
  
  @Autowired
  private ThreadPoolManager threadPoolManager;
  
  @Autowired
  private RedisUtils redis;
  
  @Autowired
  private HttpClientUtil httpClientUtil;
  
  @Autowired
  private EmailService emailService;
  
  @Value("${httpclient.dev_or_prod}")
  private String dev_or_prod;
  


  
  @Transactional
  public Object revoke(String orgId, String docStatus, String IV_N) {
    log.info("从SAP获取信息开始》》》》》》》》》{}，{}，{}", new Object[] { orgId, docStatus, IV_N });
    
    
//  获得JCO目标地址
    JCoDestination destination = this.pool.getJCoDestination();
    
    JCoFunction function2 = null;
    try {
//    获取jco函数
      function2 = destination.getRepository().getFunction("ZFI_JSI01");
    } catch (JCoException e) {
      e.printStackTrace();
      log.info(e.getMessage());
    } 
    
    if (function2 == null)
      throw new RuntimeException("ZFI_JSI01 not found in SAP.");    //函数没有在SAP中发现
    
//  通过这个函数获得参数列表 并给参数设置值 
    function2.getImportParameterList().setValue("IV_STATUS", docStatus);     //单据状态
    function2.getImportParameterList().setValue("IV_N", IV_N);               //每次取几条
    function2.getImportParameterList().setValue("IV_ID", orgId);             //公司组织号
    
    try {
      log.info("从SAP获取信息执行开始》》》》》》》》》");
      function2.execute(destination);    //通过目标地址执行这个函数   ， 这个函数   会从ERP数据库 把对应的数据抓过来
      log.info("从SAP获取信息执行结束》》》》》》》》》");  
    } catch (Exception e) {
      log.info("从SAP获取信息异常》》》》》》》》》" + e.toString());
      System.out.println(e.toString());
    } 
    
      
//  从远程参数列表中获取到待开票 参数：EX_TYPE，EX_MESSAGE  （返回的消息类型 / 内容）
    String EX_TYPE2 = function2.getExportParameterList().getString("EX_TYPE");
    System.out.println("待开票EX_TYPE " + EX_TYPE2 + ":\n");
    log.info("待开票EX_TYPE " + EX_TYPE2 + ":\n");
    String EX_MESSAGE2 = function2.getExportParameterList().getString("EX_MESSAGE");
    System.out.println("待开票EX_MESSAGE " + EX_MESSAGE2 + ":\n");
    log.info("待开票EX_MESSAGE " + EX_MESSAGE2 + ":\n");
    
    
    
//  从这个函数  从 sap中  ---获取 表 IT_TABLE_HE  
    JCoTable returnStructure2 = function2.getTableParameterList().getTable("IT_TABLE_HE");
    if (returnStructure2.getNumRows() == 0)
      return null; 
    
//  获取到所有发票头信息
    List<InvoiceHead> invoiceHeads = new ArrayList<>();
    
//  如果sap中这个 IT_TABLE_HE  |   发票头信息（抬头-公共的信息）表 不为空  ，那么获取到 发票头中所有信息 ------||||||
    for (int i = 0; i < returnStructure2.getNumRows(); i++) {
      returnStructure2.setRow(i);
      System.out.println("MANDT:" + returnStructure2.getString("MANDT"));       //客户端号
      log.info("获取到SAP待开票信息，单据号：" + returnStructure2.getString("DOC_NUM"));
      InvoiceHead invoiceHead = new InvoiceHead();
      invoiceHead.setMandt(returnStructure2.getString("MANDT"));    
      invoiceHead.setDocNum(returnStructure2.getString("DOC_NUM"));     //单据编号
      invoiceHead.setDocDate(returnStructure2.getString("DOC_DATE"));    
      invoiceHead.setDocStatus(returnStructure2.getString("DOC_STATUS"));  //单据状态  ，单据状态有哪些？
      invoiceHead.setOrgId(returnStructure2.getString("ORG_ID"));          //组织id
      invoiceHead.setOrgName(returnStructure2.getString("ORG_NAME"));      
      invoiceHead.setOrgTaxcode(returnStructure2.getString("ORG_TAXCODE"));   //开票公司税号
      invoiceHead.setOrgMachine(returnStructure2.getString("ORG_MACHINE"));   //税控机号（就是税盘号 ，主盘是0号）
      invoiceHead.setOrgAddress(returnStructure2.getString("ORG_ADDRESS"));   
      invoiceHead.setOrgTelephone(returnStructure2.getString("ORG_TELEPHONE"));
      invoiceHead.setOrgBankname(returnStructure2.getString("ORG_BANKNAME"));   
      invoiceHead.setOrgBankaccount(returnStructure2.getString("ORG_BANKACCOUNT"));
      invoiceHead.setOrgTaxexceed(returnStructure2.getBigDecimal("ORG_TAXEXCEED"));   //开票公司发票限额
      invoiceHead.setOrgControltax(returnStructure2.getString("ORG_CONTROLTAX"));     
      invoiceHead.setCustIdAr(returnStructure2.getString("CUST_ID_AR"));
      invoiceHead.setCustNameAr(returnStructure2.getString("CUST_NAME_AR"));
      invoiceHead.setCustIdBill(returnStructure2.getString("CUST_ID_BILL"));
      invoiceHead.setCustNameBill(returnStructure2.getString("CUST_NAME_BILL"));
      invoiceHead.setCustName(returnStructure2.getString("CUST_NAME"));
      invoiceHead.setCustTaxcode(returnStructure2.getString("CUST_TAXCODE"));
      invoiceHead.setCustAddress(returnStructure2.getString("CUST_ADDRESS"));
      invoiceHead.setCustTelephone(returnStructure2.getString("CUST_TELEPHONE"));
      invoiceHead.setCustBankname(returnStructure2.getString("CUST_BANKNAME"));
      invoiceHead.setCustBankaccount(returnStructure2.getString("CUST_BANKACCOUNT"));
      invoiceHead.setCustEmail(returnStructure2.getString("CUST_EMAIL"));
      invoiceHead.setCustMobile(returnStructure2.getString("CUST_MOBILE"));
      invoiceHead.setInvoiceType(returnStructure2.getString("INVOICE_TYPE"));
      invoiceHead.setInvoiceTypes(returnStructure2.getString("INVOICE_TYPES"));
      invoiceHead.setInvoiceBase(returnStructure2.getString("INVOICE_BASE"));
      invoiceHead.setInvoiceWay(returnStructure2.getString("INVOICE_WAY"));
      invoiceHead.setMergeAmt(returnStructure2.getBigDecimal("MERGE_AMT"));
      invoiceHead.setMergeQty(returnStructure2.getBigDecimal("MERGE_QTY"));
      invoiceHead.setTaxRate(returnStructure2.getString("TAX_RATE"));
      invoiceHead.setDiscountType(returnStructure2.getString("DISCOUNT_TYPE"));
      invoiceHead.setDiscountRate(returnStructure2.getBigDecimal("DISCOUNT_RATE"));
      invoiceHead.setMergeGift(returnStructure2.getString("MERGE_GIFT"));
      invoiceHead.setInvoiceList(returnStructure2.getString("INVOICE_LIST"));
      invoiceHead.setInvoiceRed(returnStructure2.getString("INVOICE_RED"));
      invoiceHead.setMergefType(returnStructure2.getString("MERGEF_TYPE"));
      invoiceHead.setMergesType(returnStructure2.getString("MERGES_TYPE"));
      invoiceHead.setGoldtaxCode(returnStructure2.getString("GOLDTAX_CODE"));
      invoiceHead.setGoldtaxNum(returnStructure2.getString("GOLDTAX_NUM"));
      invoiceHead.setUserId(returnStructure2.getString("USER_ID"));
      invoiceHead.setUserName(returnStructure2.getString("USER_NAME"));
      invoiceHead.setCheckName(returnStructure2.getString("CHECK_NAME"));
      invoiceHead.setPayeeName(returnStructure2.getString("PAYEE_NAME"));
      invoiceHead.setBillDate(returnStructure2.getString("BILL_DATE"));
      invoiceHead.setCancelDate(returnStructure2.getString("CANCEL_DATE"));
      invoiceHead.setBillGdate(returnStructure2.getString("BILL_GDATE"));
      invoiceHead.setCancelGdate(returnStructure2.getString("CANCEL_GDATE"));
      invoiceHead.setInvoiceRedReqm(returnStructure2.getString("INVOICE_RED_REQM"));
      invoiceHead.setInvoiceRedXxbm(returnStructure2.getString("INVOICE_RED_XXBM"));
      invoiceHead.setInvoiceRedFpdm(returnStructure2.getString("INVOICE_RED_FPDM"));
      invoiceHead.setInvoiceRedFphm(returnStructure2.getString("INVOICE_RED_FPHM"));
      invoiceHead.setBillRemark(returnStructure2.getString("BILL_REMARK"));
      invoiceHead.setZamountHswc(returnStructure2.getBigDecimal("ZAMOUNT_HSWC"));
      invoiceHead.setZamountWswc(returnStructure2.getBigDecimal("ZAMOUNT_WSWC"));
      invoiceHead.setZamountSewc(returnStructure2.getBigDecimal("ZAMOUNT_SEWC"));
      invoiceHead.setCreatedBy(returnStructure2.getString("CREATED_BY"));
      invoiceHead.setCreationDate(returnStructure2.getString("CREATION_DATE"));
      invoiceHead.setLastUpdatedBy(returnStructure2.getString("LAST_UPDATED_BY"));
      invoiceHead.setLastUpdatedDat(returnStructure2.getString("LAST_UPDATED_DAT"));
      invoiceHead.setAttributf1(returnStructure2.getString("ATTRIBUTF1"));
      invoiceHead.setCustProvEx(returnStructure2.getString("CUST_PROV_EX"));
      invoiceHead.setCustCity(returnStructure2.getString("CUST_CITY"));
      invoiceHead.setCustDistrict(returnStructure2.getString("CUST_DISTRICT"));
      invoiceHead.setCustAddrEx(returnStructure2.getString("CUST_ADDR_EX"));
      invoiceHead.setExName(returnStructure2.getString("EX_NAME"));
      invoiceHead.setExTelephone(returnStructure2.getString("EX_TELEPHONE"));
      if (StringUtils.isNotEmpty(this.dev_or_prod) && this.dev_or_prod.equals("dev"))
        if ("4".equals(invoiceHead.getInvoiceType())) {
          invoiceHead.setOrgName("百旺电子测试2");
          invoiceHead.setOrgTaxcode("110109500321655");
        } else {
          invoiceHead.setOrgName("百旺电子测试1");
          invoiceHead.setOrgTaxcode("110109500321654");
        }  
      invoiceHeads.add(invoiceHead);
    } 
    
    
    
//  从sap中得到 发票拆分信息
    List<InvoiceSplitLine> splitLines = new ArrayList<>();
    JCoTable returnStructure3 = function2.getTableParameterList().getTable("IT_TABLE_GR");
    
    
    for (int j = 0; j < returnStructure3.getNumRows(); j++) {
      returnStructure3.setRow(j);
      InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
      invoiceSplitLine.setMandt(returnStructure3.getString("MANDT"));
      log.info("获取到SAP待开票信息，单据号：" + returnStructure2.getString("DOC_NUM") + ",分组号：" + returnStructure3.getString("GROUP_NUM") + ",行号：" + returnStructure3.getString("DOC_LINE"));
      invoiceSplitLine.setDocNum(returnStructure3.getString("DOC_NUM"));
      invoiceSplitLine.setDocLine(returnStructure3.getString("DOC_LINE"));
      invoiceSplitLine.setGroupNum(returnStructure3.getString("GROUP_NUM"));
      invoiceSplitLine.setGroupStatus(returnStructure3.getString("GROUP_STATUS"));
      invoiceSplitLine.setGoldtaxCode(returnStructure3.getString("GOLDTAX_CODE"));
      invoiceSplitLine.setGoldtaxNum(returnStructure3.getString("GOLDTAX_NUM"));
      invoiceSplitLine.setItemName(returnStructure3.getString("ITEM_NAME"));
      invoiceSplitLine.setItemSpec(returnStructure3.getString("ITEM_SPEC"));
      invoiceSplitLine.setUnitName(returnStructure3.getString("UNIT_NAME"));
      invoiceSplitLine.setTaxCatecode(returnStructure3.getString("TAX_CATECODE"));
      invoiceSplitLine.setQuantity(returnStructure3.getBigDecimal("QUANTITY"));
      invoiceSplitLine.setTaxRate(returnStructure3.getBigDecimal("TAX_RATE"));
      invoiceSplitLine.setGiftFlag(returnStructure3.getString("GIFT_FLAG"));
      invoiceSplitLine.setZamountHsj(returnStructure3.getBigDecimal("ZAMOUNT_HSJ"));
      invoiceSplitLine.setZamountWsj(returnStructure3.getBigDecimal("ZAMOUNT_WSJ"));
      invoiceSplitLine.setZamountSej(returnStructure3.getBigDecimal("ZAMOUNT_SEJ"));
      invoiceSplitLine.setZamountHzhs(returnStructure3.getBigDecimal("ZAMOUNT_HZHS"));
      invoiceSplitLine.setZamountHzws(returnStructure3.getBigDecimal("ZAMOUNT_HZWS"));
      invoiceSplitLine.setZamountHzse(returnStructure3.getBigDecimal("ZAMOUNT_HZSE"));
      invoiceSplitLine.setZamountHsy(returnStructure3.getBigDecimal("ZAMOUNT_HSY"));
      invoiceSplitLine.setZamountWsy(returnStructure3.getBigDecimal("ZAMOUNT_WSY"));
      invoiceSplitLine.setZamountSey(returnStructure3.getBigDecimal("ZAMOUNT_SEY"));
      invoiceSplitLine.setZpriceHsj(returnStructure3.getBigDecimal("ZPRICE_HSJ"));
      log.info("===========================单价：{}" + invoiceSplitLine.getZpriceHsj());
      invoiceSplitLine.setZpriceWsj(returnStructure3.getBigDecimal("ZPRICE_WSJ"));
      invoiceSplitLine.setZpriceHsy(returnStructure3.getBigDecimal("ZPRICE_HSY"));
      invoiceSplitLine.setZpriceWsy(returnStructure3.getBigDecimal("ZPRICE_WSY"));
      invoiceSplitLine.setBillDate(returnStructure3.getString("BILL_DATE"));
      invoiceSplitLine.setCancelDate(returnStructure3.getString("CANCEL_DATE"));
      invoiceSplitLine.setBillGdate(returnStructure3.getString("BILL_GDATE"));
      invoiceSplitLine.setCancelGdate(returnStructure3.getString("CANCEL_GDATE"));
      invoiceSplitLine.setGroupCopyfrom(returnStructure3.getString("GROUP_COPYFROM"));
      invoiceSplitLine.setAttributf1(returnStructure3.getString("ATTRIBUTF1"));
      if (StringUtils.isNotEmpty(this.dev_or_prod) && this.dev_or_prod.equals("dev") && 
        StringUtils.isEmpty(invoiceSplitLine.getTaxCatecode()))
        invoiceSplitLine.setTaxCatecode("1070223010000000000"); 
        splitLines.add(invoiceSplitLine);
    } 
    
    
    
//  处理发票头信息，发票拆分信息
    if (invoiceHeads != null && invoiceHeads.size() > 0)
      if (docStatus.equals("3")) {   //如果单据状态是提交作废的状态 
        log.info("状态3》》》》");
        this.manager.update("invoice_head.invalid", (IPO)invoiceHeads.get(0));//将多个发票头信息中的第一条 更新  数据库对应的 无效状态
        
        if (splitLines != null && splitLines.size() > 0) {     //如果  有n条发票  数据
        	
          //用来存放相同单据编号，不同分组号的发票拆分信息
          List<InvoiceSplitLine> iss = new ArrayList<>();      
          InvoiceSplitLine tem = new InvoiceSplitLine();
          
          tem.setDocNum("sisis");  
          tem.setGroupNum("jfjf");   
          
          
          for (InvoiceSplitLine is : splitLines) { //遍历 发票拆分行数据中 每一项
//        	第一条是必须放的
            if (tem.getDocNum().equals("sisis") && tem.getGroupNum().equals("jfjf") && is.getGroupStatus().equals("3"))   //提交作废组
              iss.add(is); 
            
//          本条的单据编码和上一条的单据编码要相同，但是本条的分组号和上一条的分组号要不同，当然状态为提交作废
            if (tem.getDocNum().equals(is.getDocNum()) && !tem.getGroupNum().equals(is.getGroupNum()) && is.getGroupStatus().equals("3"))
              iss.add(is); 
            
            
//          
            tem.setDocNum(is.getDocNum());
            tem.setGroupNum(is.getGroupNum());
            
          }
          
          
          if ((((iss != null) ? 1 : 0) & ((iss.size() > 0) ? 1 : 0)) != 0)
            for (InvoiceSplitLine splitLine : iss)
//            对不同分组号但是 相同单据编号的发票拆分信息进行      无效作废处理
              this.manager.update("invoice_split_line.invalidOne", (IPO)splitLine);
        }
//      如果发票头信息的第一条的单据状态是  金税已开票
      } else if ("2".equals(((InvoiceHead)invoiceHeads.get(0)).getDocStatus())) {
        log.info("状态2》》》》");
        if (splitLines != null && splitLines.size() > 0) {
//        	存放
          List<InvoiceSplitLine> iss = new ArrayList<>();
          for (InvoiceSplitLine is : splitLines) {
//        	  ---只要每条发票拆分行信息 的组状态是 已提交开票的状态   就放进去处理
            if (is.getGroupStatus().equals("1"))
              iss.add(is); 
          } 
          if ((((iss != null) ? 1 : 0) & ((iss.size() > 0) ? 1 : 0)) != 0)
            for (InvoiceSplitLine splitLine : iss)
//          	  ---将每条发票拆分行信息 的组状态是 已提交开票的状态 ，单据编号是金税已开票    插入到 发票拆分行信息表
              this.manager.insert("invoice_split_line.create", (IPO)splitLine);
        } 
      } else {
//    	 传入 状态 不是2,3状态可能就是1状态，已提交开票的状态
        log.info("新增开始》》》》》》");
        try {
        	
          
//        将  发票头信息   第一项插入
          this.manager.insert("invoice_head.create", (IPO)invoiceHeads.get(0));
          
          if (splitLines != null && splitLines.size() > 0)
            for (InvoiceSplitLine splitLine : splitLines)
//            将所有的 发票拆分行信息 插入
              this.manager.insert("invoice_split_line.create", (IPO)splitLine);
          log.info("新增结束》》》》》");
        } catch (Exception e) {
          e.printStackTrace();
          log.error("从sap获取数据保存到db失败！原因：{}", e.getMessage());
        } 
      }  
    return null;
  }
  
  @Transactional
  public Object delInvoice(String docNum) throws JCoException {
    InvoiceHead ih = new InvoiceHead();
    ih.setDocNum(docNum);
    InvoiceHead invoiceHead = (InvoiceHead)this.manager.query("invoice_head.queryOne", ih);
    JCoDestination destination = this.pool.getJCoDestination();
    JCoFunction function4 = destination.getRepository().getFunction("ZFI_JSI02");
    if (function4 == null)
      throw new RuntimeException("ZFI_JSI02 not found in SAP."); 
    JCoTable options = function4.getTableParameterList().getTable("IT_TABLE_HE");
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
    if (invoiceHead.getOrgTaxexceed() != null)
      options.setValue("ORG_TAXEXCEED", invoiceHead.getOrgTaxexceed()); 
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
    if (invoiceHead.getMergeAmt() != null)
      options.setValue("MERGE_AMT", invoiceHead.getMergeAmt()); 
    if (invoiceHead.getMergeQty() != null)
      options.setValue("MERGE_QTY", invoiceHead.getMergeQty()); 
    options.setValue("TAX_RATE", invoiceHead.getTaxRate());
    options.setValue("DISCOUNT_TYPE", invoiceHead.getDiscountType());
    if (invoiceHead.getDiscountRate() != null)
      options.setValue("DISCOUNT_RATE", invoiceHead.getDiscountRate()); 
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
    if (invoiceHead.getZamountHswc() != null)
      options.setValue("ZAMOUNT_HSWC", invoiceHead.getZamountHswc()); 
    if (invoiceHead.getZamountWswc() != null)
      options.setValue("ZAMOUNT_WSWC", invoiceHead.getZamountWswc()); 
    if (invoiceHead.getZamountSewc() != null)
      options.setValue("ZAMOUNT_SEWC", invoiceHead.getZamountSewc()); 
    options.setValue("CREATED_BY", invoiceHead.getCreatedBy());
    options.setValue("CREATION_DATE", invoiceHead.getCreationDate());
    options.setValue("LAST_UPDATED_BY", invoiceHead.getLastUpdatedBy());
    options.setValue("LAST_UPDATED_DAT", invoiceHead.getLastUpdatedDat());
    options.setValue("ATTRIBUTF1", invoiceHead.getAttributf1());
    JCoTable options1 = function4.getTableParameterList().getTable("IT_TABLE_GR");
    for (InvoiceSplitLine invoiceSplitLine : invoiceHead.getInvoiceSplitLines()) {
      options1.appendRow();
      if ("1".equals(invoiceSplitLine.getGoldtaxCode()))
        invoiceSplitLine.setGoldtaxCode(""); 
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
      if (invoiceSplitLine.getQuantity() != null)
        options1.setValue("QUANTITY", invoiceSplitLine.getQuantity()); 
      if (invoiceSplitLine.getTaxRate() != null)
        options1.setValue("TAX_RATE", invoiceSplitLine.getTaxRate()); 
      options1.setValue("GIFT_FLAG", invoiceSplitLine.getGiftFlag());
      if (invoiceSplitLine.getZamountHsj() != null)
        options1.setValue("ZAMOUNT_HSJ", invoiceSplitLine.getZamountHsj()); 
      if (invoiceSplitLine.getZamountWsj() != null)
        options1.setValue("ZAMOUNT_WSJ", invoiceSplitLine.getZamountWsj()); 
      if (invoiceSplitLine.getZamountSej() != null)
        options1.setValue("ZAMOUNT_SEJ", invoiceSplitLine.getZamountSej()); 
      if (invoiceSplitLine.getZamountHzhs() != null)
        options1.setValue("ZAMOUNT_HZHS", invoiceSplitLine.getZamountHzhs()); 
      if (invoiceSplitLine.getZamountHzws() != null)
        options1.setValue("ZAMOUNT_HZWS", invoiceSplitLine.getZamountHzws()); 
      if (invoiceSplitLine.getZamountHzse() != null)
        options1.setValue("ZAMOUNT_HZSE", invoiceSplitLine.getZamountHzse()); 
      if (invoiceSplitLine.getZamountHsy() != null)
        options1.setValue("ZAMOUNT_HSY", invoiceSplitLine.getZamountHsy()); 
      if (invoiceSplitLine.getZamountWsy() != null)
        options1.setValue("ZAMOUNT_WSY", invoiceSplitLine.getZamountWsy()); 
      if (invoiceSplitLine.getZamountSey() != null)
        options1.setValue("ZAMOUNT_SEY", invoiceSplitLine.getZamountSey()); 
      if (invoiceSplitLine.getZpriceHsj() != null)
        options1.setValue("ZPRICE_HSJ", invoiceSplitLine.getZpriceHsj()); 
      if (invoiceSplitLine.getZpriceWsj() != null)
        options1.setValue("ZPRICE_WSJ", invoiceSplitLine.getZpriceWsj()); 
      if (invoiceSplitLine.getZpriceHsy() != null)
        options1.setValue("ZPRICE_HSY", invoiceSplitLine.getZpriceHsy()); 
      if (invoiceSplitLine.getZpriceWsy() != null)
        options1.setValue("ZPRICE_WSY", invoiceSplitLine.getZpriceWsy()); 
      options1.setValue("BILL_DATE", invoiceSplitLine.getBillDate());
      options1.setValue("CANCEL_DATE", invoiceSplitLine.getCancelDate());
      options1.setValue("BILL_GDATE", invoiceSplitLine.getBillGdate());
      options1.setValue("CANCEL_GDATE", invoiceSplitLine.getCancelGdate());
      options1.setValue("GROUP_COPYFROM", invoiceSplitLine.getGroupCopyfrom());
      options1.setValue("ATTRIBUTF1", invoiceSplitLine.getAttributf1());
    } 
    try {
      function4.execute(destination);
    } catch (AbapException e) {
      log.info(e.toString());
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("msg", "错误原因：执行回传解锁异常");
      jSONObject.put("success", Boolean.valueOf(false));
      throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, jSONObject);
    } 
    String EX_TYPE = function4.getExportParameterList().getString("EX_TYPE");
    System.out.println("发票回传EX_TYPE " + EX_TYPE + ":\n");
    String EX_MESSAGE = function4.getExportParameterList().getString("EX_MESSAGE");
    log.info(invoiceHead.getDocNum() + "发票回传EX_MESSAGE " + EX_MESSAGE + ":\n");
    if (EX_TYPE.equals("S")) {
      invoiceHead.setIssync("2");
      this.manager.update("invoice_head.changeIssyncById", (IPO)invoiceHead);
    } else {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("msg", EX_MESSAGE);
      jSONObject.put("success", Boolean.valueOf(false));
      throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, jSONObject);
    } 
    if ("1".equals(invoiceHead.getDocStatus()))
      this.manager.delete("invoice_head.del", (IPO)invoiceHead);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("msg", "撤销成功");
    jsonObject.put("success", Boolean.valueOf(true));
    return jsonObject;
  }

  
  
  
  @Override
  public Object unlock(String orgId) throws JCoException {
//	发票头信息
    InvoiceHead ih = new InvoiceHead();
    
//  受票组织id
    ih.setOrgId(orgId);
    
//  根据开票组织id(开票公司的编码)查询 ，并只要的到结果中的一个
    InvoiceHead invoiceHead = (InvoiceHead)this.manager.query("invoice_head.queryOneByCondition", ih);
    
//  如果开票组织id 没有对应的发票头数据（即使有单据号不为空），报错
    if (invoiceHead == null || StringUtils.isEmpty(invoiceHead.getDocNum())) {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("msg", "撤销成功：无可撤销单据");
      jSONObject.put("success", Boolean.valueOf(true));
      throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, jSONObject);
    } 
    
    
//  通过组织得到发票分组明细(一个组织 一个客户 一个分组     有多个发票 ，通过什么字段关联了？)
    for (InvoiceSplitLine invoiceSplitLine : invoiceHead.getInvoiceSplitLines()) {
//    	-如果说有一个发票的 （金税发票编码 ） 是空的 或者  组状态不是1 ，那么就报错
      if (!invoiceSplitLine.getGroupStatus().equals("1") || !StringUtils.isEmpty(invoiceSplitLine.getGoldtaxCode())) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("msg", "撤销失败：当前单据状态不允许撤销");
        jSONObject.put("success", Boolean.valueOf(false));
        throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, jSONObject);
      } 
    }
    
    
    
//  检验完每个组织的所有发票正确无误后，获得jco远程连接
    JCoDestination destination = this.pool.getJCoDestination();
//        ---调用目标函数 ZFI_JSI02
    JCoFunction function4 = destination.getRepository().getFunction("ZFI_JSI02");
    if (function4 == null)
      throw new RuntimeException("ZFI_JSI02 not found in SAP."); 
    
//  通过这个函数获取到表，并给 表中的字段 把发票头的信息(开票公司信息，开票客户信息，其他开票信息)  和  这个组织的所有发票信息 全部传给   传给sap
    JCoTable options = function4.getTableParameterList().getTable("IT_TABLE_HE");
    options.appendRow();
    options.setValue("MANDT", invoiceHead.getMandt());   //客户端
    options.setValue("DOC_NUM", invoiceHead.getDocNum());   //单据编号                     ************
    options.setValue("DOC_DATE", invoiceHead.getDocDate());  //单据日期
    options.setValue("DOC_STATUS", invoiceHead.getDocStatus());  //单据状态            ****
    options.setValue("ORG_ID", invoiceHead.getOrgId());           //开票公司编码(组织号)*******
    options.setValue("ORG_NAME", invoiceHead.getOrgName());       //开票公司名
    options.setValue("ORG_TAXCODE", invoiceHead.getOrgTaxcode());    //开票公司 税号**********
    options.setValue("ORG_MACHINE", invoiceHead.getOrgMachine());    //税控机号**************
    options.setValue("ORG_ADDRESS", invoiceHead.getOrgAddress());     //开票公司地址
    options.setValue("ORG_TELEPHONE", invoiceHead.getOrgTelephone()); //开票公司电话
    options.setValue("ORG_BANKNAME", invoiceHead.getOrgBankname());   //开票公司银行
    options.setValue("ORG_BANKACCOUNT", invoiceHead.getOrgBankaccount()); //开票公司银行账号
    if (invoiceHead.getOrgTaxexceed() != null)    
      options.setValue("ORG_TAXEXCEED", invoiceHead.getOrgTaxexceed());   //开票公司发票限额
    options.setValue("ORG_CONTROLTAX", invoiceHead.getOrgControltax());   //开票公司选项--
    options.setValue("CUST_ID_AR", invoiceHead.getCustIdAr());            //应收客户编码
    options.setValue("CUST_NAME_AR", invoiceHead.getCustNameAr());        //应收客户名称
    options.setValue("CUST_ID_BILL", invoiceHead.getCustIdBill());        //开票客户编码
    options.setValue("CUST_NAME_BILL", invoiceHead.getCustNameBill());    //开票客户名称
    options.setValue("CUST_NAME", invoiceHead.getCustName());             //开票客户全程
    options.setValue("CUST_TAXCODE", invoiceHead.getCustTaxcode());       //开票客户税号
    options.setValue("CUST_ADDRESS", invoiceHead.getCustAddress());       //开票客户地址
    options.setValue("CUST_TELEPHONE", invoiceHead.getCustTelephone());   
    options.setValue("CUST_BANKNAME", invoiceHead.getCustBankname());     
    options.setValue("CUST_BANKACCOUNT", invoiceHead.getCustBankaccount()); 
    options.setValue("CUST_EMAIL", invoiceHead.getCustEmail());  
    options.setValue("CUST_MOBILE", invoiceHead.getCustMobile());
    options.setValue("INVOICE_TYPE", invoiceHead.getInvoiceType());      //发票类型
    options.setValue("INVOICE_TYPES", invoiceHead.getInvoiceTypes());     //发票子类型
    options.setValue("INVOICE_BASE", invoiceHead.getInvoiceBase());     //基准方式
    options.setValue("INVOICE_WAY", invoiceHead.getInvoiceWay());       //开票方式
    if (invoiceHead.getMergeAmt() != null)                              //拼单金额
      options.setValue("MERGE_AMT", invoiceHead.getMergeAmt()); 
    if (invoiceHead.getMergeQty() != null)
      options.setValue("MERGE_QTY", invoiceHead.getMergeQty());        //拼单数量
    options.setValue("TAX_RATE", invoiceHead.getTaxRate());             //税率
    options.setValue("DISCOUNT_TYPE", invoiceHead.getDiscountType());   //折扣方式
    if (invoiceHead.getDiscountRate() != null)                          
      options.setValue("DISCOUNT_RATE", invoiceHead.getDiscountRate());  //折扣率
    options.setValue("MERGE_GIFT", invoiceHead.getMergeGift());           //是否合并赠品行
    options.setValue("INVOICE_LIST", invoiceHead.getInvoiceList());       //是否清单开票
    options.setValue("INVOICE_RED", invoiceHead.getInvoiceRed());       // 红字发票
    options.setValue("MERGEF_TYPE", invoiceHead.getMergefType());       //一次合并方式
    options.setValue("MERGES_TYPE", invoiceHead.getMergesType());       //二次合并方式
    options.setValue("GOLDTAX_CODE", invoiceHead.getGoldtaxCode());     //金税发票代码*********
    options.setValue("GOLDTAX_NUM", invoiceHead.getGoldtaxNum());       //金税发票号码*********
    options.setValue("USER_ID", invoiceHead.getUserId());               //开票人编码
    options.setValue("USER_NAME", invoiceHead.getUserName());           //开票人姓名
    options.setValue("CHECK_NAME", invoiceHead.getCheckName());          //复核人姓名
    options.setValue("PAYEE_NAME", invoiceHead.getPayeeName());          //收款人姓名
    options.setValue("BILL_DATE", invoiceHead.getBillDate());             //提交开票的日期
    options.setValue("CANCEL_DATE", invoiceHead.getCancelDate());        //提交作废的日期
    options.setValue("BILL_GDATE", invoiceHead.getBillGdate());           //金税开票日期
    options.setValue("CANCEL_GDATE", invoiceHead.getCancelGdate());        //金税作废日期
    options.setValue("INVOICE_RED_REQM", invoiceHead.getInvoiceRedReqm());  //红字申请原因(退货退款)
    options.setValue("INVOICE_RED_XXBM", invoiceHead.getInvoiceRedXxbm());  //红字申请单号（这个单号是什么）
    options.setValue("INVOICE_RED_FPDM", invoiceHead.getInvoiceRedFpdm());   //蓝字发票代码********
    options.setValue("INVOICE_RED_FPHM", invoiceHead.getInvoiceRedFphm());    //蓝字发票号码 ************
    options.setValue("BILL_REMARK", invoiceHead.getBillRemark());            //开票备注
    if (invoiceHead.getZamountHswc() != null)
      options.setValue("ZAMOUNT_HSWC", invoiceHead.getZamountHswc());      //含税净金额
    if (invoiceHead.getZamountWswc() != null)                       
      options.setValue("ZAMOUNT_WSWC", invoiceHead.getZamountWswc());       //不含税净金额
    if (invoiceHead.getZamountSewc() != null)
      options.setValue("ZAMOUNT_SEWC", invoiceHead.getZamountSewc());      //净税额
    options.setValue("CREATED_BY", invoiceHead.getCreatedBy());            //创建者id(是什么)
    options.setValue("CREATION_DATE", invoiceHead.getCreationDate());     
    options.setValue("LAST_UPDATED_BY", invoiceHead.getLastUpdatedBy());  
    options.setValue("LAST_UPDATED_DAT", invoiceHead.getLastUpdatedDat());
    options.setValue("ATTRIBUTF1", invoiceHead.getAttributf1());       //自定义字段
    JCoTable options1 = function4.getTableParameterList().getTable("IT_TABLE_GR");
    
//    发票分组明细(一个组织 一个客户   一个分组  有多个发票)
    for (InvoiceSplitLine invoiceSplitLine : invoiceHead.getInvoiceSplitLines()) {
      options1.appendRow();
      options1.setValue("MANDT", invoiceSplitLine.getMandt());       //客户端  ---sap有个客户端口
      options1.setValue("DOC_NUM", invoiceSplitLine.getDocNum());    //单据编码
      options1.setValue("DOC_LINE", invoiceSplitLine.getDocLine());  //行号 （是？）
      options1.setValue("GROUP_NUM", invoiceSplitLine.getGroupNum());   //分组号（是？）
      options1.setValue("GROUP_STATUS", invoiceSplitLine.getGroupStatus()); //分组状态？
      options1.setValue("GOLDTAX_CODE", invoiceSplitLine.getGoldtaxCode()); //金税发票代码*****
      options1.setValue("GOLDTAX_NUM", invoiceSplitLine.getGoldtaxNum());   //金税发票号码*****
      options1.setValue("ITEM_NAME", invoiceSplitLine.getItemName());    //品名
      options1.setValue("ITEM_SPEC", invoiceSplitLine.getItemSpec());    //规格型号
      options1.setValue("UNIT_NAME", invoiceSplitLine.getUnitName());    //计量单位
      options1.setValue("TAX_CATECODE", invoiceSplitLine.getTaxCatecode()); //税收分类编码
      if (invoiceSplitLine.getQuantity() != null)              
        options1.setValue("QUANTITY", invoiceSplitLine.getQuantity());      //数量
      if (invoiceSplitLine.getTaxRate() != null)
        options1.setValue("TAX_RATE", invoiceSplitLine.getTaxRate());      //税率
      options1.setValue("GIFT_FLAG", invoiceSplitLine.getGiftFlag());      //是否赠品行
      if (invoiceSplitLine.getZamountHsj() != null)
        options1.setValue("ZAMOUNT_HSJ", invoiceSplitLine.getZamountHsj()); //含税净金额
      if (invoiceSplitLine.getZamountWsj() != null)
        options1.setValue("ZAMOUNT_WSJ", invoiceSplitLine.getZamountWsj());  //不含税净金额
      if (invoiceSplitLine.getZamountSej() != null)
        options1.setValue("ZAMOUNT_SEJ", invoiceSplitLine.getZamountSej());  //净税额
      if (invoiceSplitLine.getZamountHzhs() != null)
        options1.setValue("ZAMOUNT_HZHS", invoiceSplitLine.getZamountHzhs()); //行折含税金额
      if (invoiceSplitLine.getZamountHzws() != null)
        options1.setValue("ZAMOUNT_HZWS", invoiceSplitLine.getZamountHzws());   //行折未税金额
      if (invoiceSplitLine.getZamountHzse() != null)
        options1.setValue("ZAMOUNT_HZSE", invoiceSplitLine.getZamountHzse()); //行折税额
      if (invoiceSplitLine.getZamountHsy() != null)
        options1.setValue("ZAMOUNT_HSY", invoiceSplitLine.getZamountHsy());   //含税原金额
      if (invoiceSplitLine.getZamountWsy() != null)
        options1.setValue("ZAMOUNT_WSY", invoiceSplitLine.getZamountWsy());  //不含税原金额
      if (invoiceSplitLine.getZamountSey() != null)
        options1.setValue("ZAMOUNT_SEY", invoiceSplitLine.getZamountSey()); 
      if (invoiceSplitLine.getZpriceHsj() != null)
        options1.setValue("ZPRICE_HSJ", invoiceSplitLine.getZpriceHsj()); 
      if (invoiceSplitLine.getZpriceWsj() != null)
        options1.setValue("ZPRICE_WSJ", invoiceSplitLine.getZpriceWsj()); 
      if (invoiceSplitLine.getZpriceHsy() != null)
        options1.setValue("ZPRICE_HSY", invoiceSplitLine.getZpriceHsy()); 
      if (invoiceSplitLine.getZpriceWsy() != null)
        options1.setValue("ZPRICE_WSY", invoiceSplitLine.getZpriceWsy()); 
      options1.setValue("BILL_DATE", invoiceSplitLine.getBillDate());      //提交开票日期
      options1.setValue("CANCEL_DATE", invoiceSplitLine.getCancelDate());   //提交作废日期
      options1.setValue("BILL_GDATE", invoiceSplitLine.getBillGdate());    //金税开票日期
      options1.setValue("CANCEL_GDATE", invoiceSplitLine.getCancelGdate());  //金税作废日期
      options1.setValue("GROUP_COPYFROM", invoiceSplitLine.getGroupCopyfrom());  
      options1.setValue("ATTRIBUTF1", invoiceSplitLine.getAttributf1());
    } 
    try {
      function4.execute(destination);           //执行函数，传递数据，发票回传
    } catch (AbapException e) {
      log.info(e.toString());
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("msg", "错误原因：执行回传解锁异常");
      jSONObject.put("success", Boolean.valueOf(false));
      throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, jSONObject);
    } 
    String EX_TYPE = function4.getExportParameterList().getString("EX_TYPE");
    System.out.println("发票回传EX_TYPE " + EX_TYPE + ":\n");
    String EX_MESSAGE = function4.getExportParameterList().getString("EX_MESSAGE");
    log.info(invoiceHead.getDocNum() + "发票回传EX_MESSAGE " + EX_MESSAGE + ":\n");
    if (EX_TYPE.equals("S")) {
      invoiceHead.setIssync("2");                                                       //是否同步
      this.manager.update("invoice_head.changeIssyncById", (IPO)invoiceHead);
    } else {
      JSONObject jSONObject = new JSONObject();
      jSONObject.put("msg", EX_MESSAGE);
      jSONObject.put("success", Boolean.valueOf(false));
      throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, jSONObject);
    } 
    this.manager.delete("invoice_head.del", (IPO)invoiceHead);        //回传后删掉这个发票头信息
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("msg", "撤销成功");
    jsonObject.put("success", Boolean.valueOf(true));
    return jsonObject;
  }

  
  private static String bc(String spbm) {
    if (spbm.length() < 19) {
      spbm = spbm + "0"; 
      spbm = bc(spbm);
      return spbm;
    } 
    return spbm;
  }
  

  

  
  public Object loadData() {
    this.threadPoolManager.threadPool.execute(new Runnable() {
          public void run() {
            InvoiceHeadServiceImpl.this.revoke("2200", "1", "1");
          }
        });

    return null;
  }
  
  private static void js(BigDecimal b1) {
    b1 = b1.add(BigDecimal.TEN);
    System.out.println("nei:" + b1);
  }
  
  private static void li(List<String> list) {
    list.add("1");
  }
  
  private synchronized Boolean shuicha(String hsje, String wsje, String se, String sl) {
    log.info(hsje + "," + wsje + "," + se + "," + sl);
    if (hsje != "" && hsje != null && se != null && se != "" && sl != null && sl != "") {
      BigDecimal shuilv = (new BigDecimal(sl)).divide(BigDecimal.TEN.multiply(BigDecimal.TEN), 2, 4);
      BigDecimal ce = (new BigDecimal(hsje)).multiply(shuilv).divide(BigDecimal.ONE.add(shuilv), 2, 4);
      if (ce.subtract(new BigDecimal(se)).subtract(new BigDecimal("0.06")).compareTo(BigDecimal.ZERO) > 0 || (new BigDecimal(se))
        .subtract(ce).subtract(new BigDecimal("0.06")).compareTo(BigDecimal.ZERO) > 0)
        return Boolean.valueOf(false); 
    } 
    if (wsje != "" && wsje != null && se != null && se != "" && sl != null && sl != "") {
      BigDecimal shuilv = (new BigDecimal(sl)).divide(BigDecimal.TEN.multiply(BigDecimal.TEN), 2, 4);
      BigDecimal ce = (new BigDecimal(wsje)).multiply(shuilv);
      if (ce.subtract(new BigDecimal(se)).subtract(new BigDecimal("0.06")).compareTo(BigDecimal.ZERO) > 0 || (new BigDecimal(se))
        .subtract(ce).subtract(new BigDecimal("0.06")).compareTo(BigDecimal.ZERO) > 0)
        return Boolean.valueOf(false); 
    } 
    return Boolean.valueOf(true);
  }
  
  private int rand() {
    int max = 999999999, min = 100000000;
    int ran2 = (int)(Math.random() * (max - min) + min);
    return ran2;
  }
  

  

  

  
  private void errLog(String doc_num, String group_num, String returnMessage) {
    InvoiceSplitLine splitLine = new InvoiceSplitLine();
    splitLine.setDocNum(doc_num);
    splitLine.setGroupNum(group_num);
    splitLine.setAttribute1(returnMessage);
    this.manager.update("invoice_split_line.errorLog", (IPO)splitLine);
  }
  

  
  private String getHM(String doc_num) {
    String hm = "";
    InvoiceSplitLine splitLine = new InvoiceSplitLine();
    splitLine.setDocNum(doc_num);
    List<InvoiceSplitLine> splitLines = (List<InvoiceSplitLine>) this.manager.list("invoice_split_line.getHM", splitLine);
    if (splitLines != null && splitLines.size() > 0) {
      long fphm0 = 0L;
      int i = 0;
      for (InvoiceSplitLine line : splitLines) {
        log.info("goldtax_num:" + line.getGoldtaxNum());
        if (line.getGoldtaxNum() != null && line.getGoldtaxNum().trim().length() == 0)
          continue; 
        long fphm1 = Long.parseLong(line.getGoldtaxNum());
        String sfphm1 = "" + fphm1;
        if (i == 0) {
          hm = formatHm(sfphm1);
          fphm0 = fphm1;
        } else if (fphm1 - 1L == fphm0) {
          if (hm.contains("-") && "-".equals(hm.substring(hm.length() - 3, hm.length() - 2))) {
            hm = hm.substring(0, hm.length() - 2) + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
          } else {
            hm = formatHm(hm) + "-" + sfphm1.substring(sfphm1.length() - 2, sfphm1.length());
          } 
          fphm0 = fphm1;
        } else if (fphm1 != fphm0) {
          fphm0 = fphm1;
          hm = hm + " " + formatHm(fphm0 + "");
        } 
        i++;
      } 
    } 
    return hm;
  }
  
  private String formatHm(String hm) {
    String fphm = "";
    if (hm.length() == 7) {
      fphm = "0" + hm;
    } else if (hm.length() == 6) {
      fphm = "00" + hm;
    } else if (hm.length() == 5) {
      fphm = "000" + hm;
    } else if (hm.length() == 4) {
      fphm = "0000" + hm;
    } else if (hm.length() == 3) {
      fphm = "00000" + hm;
    } else if (hm.length() == 2) {
      fphm = "000000" + hm;
    } else if (hm.length() == 1) {
      fphm = "0000000" + hm;
    } else {
      fphm = hm;
    } 
    return fphm;
  }
  
  private static int test(int i) {
    System.out.println(">>>>>>>>>" + i);
    try {
      Thread.sleep(1000L);
      if (i == 10)
        return 111; 
      i = test(++i);
      System.out.println("return:" + i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
    return i;
  }
  

  
  public Pages<List<VInvoiceHead>> pages_en(VInvoiceHead t, Integer pageSize, Integer pageNum) {
    EasyPage page = new EasyPage();
    page.pageNum(pageNum.intValue());
    page.pageSize(pageSize.intValue());
    return (Pages<List<VInvoiceHead>>) this.manager.pages("invoice_head.page_en", t, (Page)page);
  }
  
  public Pages<List<VInvoiceHead>> dy_pages_en(VInvoiceHead t, Integer pageSize, Integer pageNum) {
    EasyPage page = new EasyPage();
    page.pageNum(pageNum.intValue());
    page.pageSize(pageSize.intValue());
    return (Pages<List<VInvoiceHead>>) this.manager.pages("invoice_head.dy_page_en", t, (Page)page);
  }
  
  public Object dy_list_en(VInvoiceHead t) {
    return this.manager.list("invoice_head.dy_page_en", t);
  }
  
  public Object waitSend() {
    String css_style = "<div style=\"border-bottom: dashed 1px #666;margin:8px 0px 20px 0px\"></div>";
    VWaitSend t = new VWaitSend();
    List<VWaitSend> wss = (List<VWaitSend>) this.manager.list("invoice_head.wait_send", t);
    if (wss != null && wss.size() > 0) {
      String doc_num = "";
      String sms_fphm = "";
      String content = "";
      String sms_content = "";
      String yjdz = "";
      String gfmc = "";
      String sjhm = "";
      BigDecimal fpze = BigDecimal.ZERO;
      for (int i = 0; i < wss.size(); i++) {
        VWaitSend ws = wss.get(i);
        String djzj = ws.getDoc_num();
        String email = ws.getCust_email();
        String mobile = ws.getPhone();
        String khqc = ws.getCust_name();
        String c_fpdm = ws.getGoldtax_code();
        String c_fphm = ws.getGoldtax_num();
        String c_bhsje = ws.getHsje();
        String c_url = ws.getE_pdf_url();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!gfmc.equals(""))
          if (!gfmc.equals(khqc)) {
            if (doc_num.length() > 2)
              doc_num = doc_num.substring(0, doc_num.length() - 1); 
            log.info("需要更新send的doc_num:" + doc_num);
            String emial_fr = "尊敬的" + gfmc + "," + sdf.format(new Date()) + "发票已开具" + fpze.toString() + "元含税，共" + (sms_fphm.length() - sms_fphm.replace(",", "").length()) + "张发票，发票信息见下文。专票预计在次月20日邮寄到您处。可先在国家电子税局网上勾选认证。请勿回复本条信息！<公牛集团财务>";
            content = emial_fr + css_style + content;
            try {
              sendEmail(gfmc, yjdz, doc_num, content);
              log.info(gfmc + yjdz + ":推送邮件内容内容：" + content);
              sendSms(gfmc, sjhm, sms_content, doc_num);
              log.info(gfmc, sjhm + ":发送短信内容：" + sms_content);
            } catch (Exception e) {
              e.printStackTrace();
              log.error("推送异常：" + gfmc + e.getMessage());
            } 
            content = "";
            doc_num = "";
            sms_fphm = "";
            sms_content = "";
            fpze = BigDecimal.ZERO;
          }  
        yjdz = email;
        sjhm = mobile;
        gfmc = khqc;
        doc_num = doc_num + "'" + djzj + "',";
        sms_fphm = sms_fphm + c_fphm + ",";
        fpze = fpze.add(new BigDecimal(c_bhsje));
        content = content + "发票代码：" + c_fpdm + ", 发票号码：" + c_fphm + " ,开票金额：￥" + c_bhsje + " ";
        if (StringUtils.isNotEmpty(c_url))
          content = content + "直接下载PDF：<a href = \"" + c_url + "\" target = _blank> " + c_url + "</a></br>"; 
        content = content + "</br>";
        sms_content = "尊敬的" + gfmc + "," + sdf.format(new Date()) + "发票已开具" + fpze.toString() + "元含税，共" + (sms_fphm.length() - sms_fphm.replace(",", "").length()) + "张发票，号码分别是：" + sms_fphm + "。专票预计在次月20日邮寄到您处。<公牛集团财务>";
        if (i + 1 == wss.size()) {
          if (doc_num.length() > 0)
            doc_num = doc_num.substring(0, doc_num.length() - 1); 
          log.info("需要更新send的doc_num:" + doc_num);
          String emial_fr = "尊敬的" + gfmc + "," + sdf.format(new Date()) + "发票已开具" + fpze.toString() + "元含税，共" + (sms_fphm.length() - sms_fphm.replace(",", "").length()) + "张发票，发票信息见下文。发票预计在次月20日邮寄到您处。可先在国家电子税局网上勾选认证。请勿回复本条信息！<公牛集团财务>";
          content = emial_fr + css_style + content;
          try {
            sendEmail(gfmc, yjdz, doc_num, content);
            sendSms(gfmc, sjhm, sms_content, doc_num);
            log.info(gfmc, sjhm + ":发送短信内容：" + sms_content);
            log.info(gfmc, yjdz + ":发送邮件内容：" + content);
          } catch (Exception e) {
            e.printStackTrace();
            log.error("推送异常：" + gfmc + e.getMessage());
          } 
          sms_fphm = "";
          sms_content = "";
          content = "";
          doc_num = "";
          fpze = BigDecimal.ZERO;
        } 
      } 
    } 
    return null;
  }
  
  private void sendEmail(String gfmc, String email, String doc_num, String content) {
    if (StringUtils.isEmpty(email)) {
      log.info("doc_num:{}无邮箱", doc_num);
    } else {
      log.info("doc_num:{}有邮箱发送邮件", doc_num);
      Map<String, String> map = this.emailService.sendComplicated(content, email);
      if ("true".equals(map.get("success"))) {
        saveSendMsg(gfmc, content, "1", "1", email);
      } else {
        saveSendMsg(gfmc, (new StringBuilder()).append(map.get("msg")).append(content).toString(), "2", "1", email);
      } 
      updateSend(doc_num, "1");
    } 
  }
  
  private void sendSms(String gfmc, String mobile, String content, String docNum) {
    if (StringUtils.isEmpty(mobile)) {
      log.error("单据号：{}中无手机号码无法发送短信", docNum);
      updateSend(docNum, "2");
      return;
    } 
    SmsServiceImplService service = new SmsServiceImplService();
    ISmsService sms = service.getSmsServiceImplPort();
    JSONObject json = new JSONObject();
    json.put("mobiles", mobile);
    json.put("funCode", "1002");
    json.put("content", content);
    json.put("sendTime", "");
    ApiResult result = sms.sendSms("027357b2-d36c-479b-b960-540b73c0e99a", json.toJSONString());
    String message = result.getMessage();
    log.info("短信message:" + message);
    String resultCode = result.getResultCode();
    log.info("短信resultCode:" + resultCode);
    if ("0".equals(resultCode)) {
      saveSendMsg(gfmc, content, "1", "2", mobile);
      log.info("{}短信发送成功", mobile);
    } else {
      log.error("{}短信发送失败code:{},{}", new Object[] { mobile, resultCode, message });
      saveSendMsg(gfmc, message + content, "2", "2", mobile);
    } 
    updateSend(docNum, "1");
  }
  
  private void updateSend(String docNum, String send) {
    InvoiceHead t = new InvoiceHead();
    t.setDocNum(docNum);
    t.setSend(send);
    log.info("更新send：" + docNum);
    this.manager.update("invoice_head.update_send", (IPO)t);
    log.info("更新send成功：" + docNum);
  }
  
  private void saveSendMsg(String gfmc, String content, String success, String sendtype, String sendno) {
    try {
      SendMsg sendMsg = new SendMsg();
      sendMsg.setContent(content);
      sendMsg.setSendno(sendno);
      sendMsg.setSendtime(new Date());
      sendMsg.setGfmc(gfmc);
      sendMsg.setSendtype(sendtype);
      sendMsg.setSuccess(success);
      log.info("更新sendMsg：" + gfmc);
      this.manager.insert("send_msg.create", (IPO)sendMsg);
      log.info("更新sendMsg成功：" + gfmc);
    } catch (Exception e) {
      e.printStackTrace();
      log.info("更新sendMsg异常：" + e.getMessage());
    } 
  }
  

  

  
  private String setkpsl(String s) {
    if (s.isEmpty())
      return null; 
    if (s.indexOf(".") > 0) {
      s = s.replaceAll("0+?$", "");
      s = s.replaceAll("[.]$", "");
    } 
    return s;
  }
  
  private String setssflbm(String ssflbm) {
    if (ssflbm.isEmpty())
      return null; 
    if (ssflbm.trim().length() < 19) {
      ssflbm = ssflbm + "0";
      ssflbm = setssflbm(ssflbm);
    } 
    if (ssflbm.trim().length() == 19)
      return ssflbm; 
    return ssflbm;
  }
  
  public Pages<List<SendMsg>> pages_send_msg(@RequestBody SendMsg sendMsg, Integer pageSize, Integer pageNum) {
    EasyPage page = new EasyPage();
    page.pageNum(pageNum.intValue());
    page.pageSize(pageSize.intValue());
    return (Pages<List<SendMsg>>) this.manager.pages("send_msg.page", sendMsg, (Page)page);
  }
  

  
  public Object exWaitSend() {
    VExWaitSend t = new VExWaitSend();
    List<VExWaitSend> ews_list = (List<VExWaitSend>) this.manager.list("invoice_head.ex_wait_send", t);
    for (VExWaitSend ews : ews_list) {
      String phone = ews.getExTelephone();
      String exnum = ews.getExnum();
      String custName = ews.getCustName();
      try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sms_content = "尊敬的" + custName + ",上月发票已于" + sdf.format(new Date()) + "寄出，快递单号：" + exnum + "。请注意查收！<公牛集团财务>";
        sendExSms(exnum, phone, sms_content, custName);
      } catch (Exception e) {
        e.printStackTrace();
        log.error("发送邮寄短信异常，快递单号：{},客户名称：{},原因：{}", new Object[] { exnum, custName, e.getMessage() });
      } 
    } 
    return null;
  }
  
  private void sendExSms(String exnum, String mobile, String content, String gfmc) {
    if (StringUtils.isEmpty(mobile)) {
      log.error("快递单号：{}对应客户中无邮寄号码无法发送短信", exnum);
      updateExSend(exnum, "2");
      return;
    } 
    SmsServiceImplService service = new SmsServiceImplService();
    ISmsService sms = service.getSmsServiceImplPort();
    JSONObject json = new JSONObject();
    json.put("mobiles", mobile);
    json.put("funCode", "1002");
    json.put("content", content);
    json.put("sendTime", "");
    ApiResult result = sms.sendSms("027357b2-d36c-479b-b960-540b73c0e99a", json.toJSONString());
    String message = result.getMessage();
    log.info("短信message:" + message);
    String resultCode = result.getResultCode();
    log.info("短信resultCode:" + resultCode);
    if ("0".equals(resultCode)) {
      saveSendMsg(gfmc, content, "1", "2", mobile);
      log.info("{}短信发送成功", mobile);
    } else {
      log.error("{}短信发送失败code:{},{}", new Object[] { mobile, resultCode, message });
      saveSendMsg(gfmc, message + content, "2", "2", mobile);
    } 
    updateExSend(exnum, "2");
  }
  
  private void updateExSend(String exnum, String exbz) {
    Express express = new Express();
    express.setExnum(exnum);
    this.manager.update("invoice_split_line.updateExbzByExnum", (IPO)express);
  }
  
  private File multipartFileToFile(MultipartFile file) throws Exception {
    File toFile = null;
    if (file.equals("") || file.getSize() <= 0L) {
      file = null;
    } else {
      InputStream ins = null;
      ins = file.getInputStream();
      toFile = new File(file.getOriginalFilename());
      inputStreamToFile(ins, toFile);
      ins.close();
    } 
    return toFile;
  }
  
  private static void inputStreamToFile(InputStream ins, File file) {
    try {
      OutputStream os = new FileOutputStream(file);
      int bytesRead = 0;
      byte[] buffer = new byte[8192];
      while ((bytesRead = ins.read(buffer, 0, 8192)) != -1)
        os.write(buffer, 0, bytesRead); 
      os.close();
      ins.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }


  private void validateServiceStart(final String key) {
    final Object v = this.redis.get(key);
    if (v == null) {
      final JSONObject jsonObject = new JSONObject();
      jsonObject.put("msg", (Object)"缓存服务异常,开票软件未开启。");
      jsonObject.put("success", (Object)false);
      throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, (Object)jsonObject);
    }
    final String val = "" + v;
    if (!"1".equals(val)) {
      final JSONObject jsonObject2 = new JSONObject();
      jsonObject2.put("msg", (Object)"请确认中航开票服务开启后重试");
      jsonObject2.put("success", (Object)false);
      throw new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR, (Object)jsonObject2);
    }
  }



  private com.zhys.invoice.po.InvoiceHead getHead(final String docNum) {
    final com.zhys.invoice.po.InvoiceHead head = new com.zhys.invoice.po.InvoiceHead();
    head.setDocNum(docNum);
    final com.zhys.invoice.po.InvoiceHead ih = (com.zhys.invoice.po.InvoiceHead)this.manager.query("invoice_head.queryHead", (Object)head);
    return ih;
  }

  private List<com.zhys.invoice.po.InvoiceSplitLine> getBodys(final String docNum) {
    final com.zhys.invoice.po.InvoiceSplitLine invoiceSplitLine = new com.zhys.invoice.po.InvoiceSplitLine();
    invoiceSplitLine.setDocNum(docNum);
    final List<com.zhys.invoice.po.InvoiceSplitLine> bodys = (List<com.zhys.invoice.po.InvoiceSplitLine>)this.manager.list("invoice_split_line.queryList", (Object)invoiceSplitLine);
    return bodys;
  }




}
