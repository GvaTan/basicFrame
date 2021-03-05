package com.zhys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lycheeframework.core.model.IPO;
import com.zhys.EntityValidate.EntityValidate;
import com.zhys.excel.ExcelCell;
import com.zhys.exception.BusinessException;
import com.zhys.invoice.po.*;
import com.zhys.po.CustomerMsg;
import com.zhys.po.InvoiceHeadDto;
import com.zhys.po.InvoiceReVo;
import com.zhys.po.InvoiceSplitLineDto;
import com.zhys.redis.RedisUtils;
import com.zhys.result.ResultCode;
import com.zhys.service.SQLManager;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.cmp.kit.EasyPage;
import com.zhys.utils.POJOConvertUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhys.base.BaseApiService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import com.zhys.service.InvoiceHeadService;

@Slf4j
@RestController
public class InvoiceHeadServiceImpl extends BaseApiService implements InvoiceHeadService{
	
	@Autowired
	private SQLManager manager;

	@Autowired
	private RedisUtils redis;
	
	@Override
	@Transactional
	public Integer save(@RequestBody InvoiceHead invoiceHead,String state){

		boolean noCode = false;

		invoiceHead.setDocStatus(state);
		/**
		 * 设置销方信息
		 */
		invoiceHead.setOrgName("浙江省天正设计工程有限公司");
		invoiceHead.setOrgTaxcode("913300007420055726");
		invoiceHead.setOrgMachine("00");
		invoiceHead.setOrgAddress("浙江省杭州市西湖区双龙街199号金色西溪商务中心2号楼");
		invoiceHead.setOrgTelephone("0571-88362953");
		invoiceHead.setOrgBankname("工行莫干山路支行");
		invoiceHead.setOrgBankaccount("1202026109900009712");
		invoiceHead.setUserId("1");
		invoiceHead.setUserName("胡小蓓");
		invoiceHead.setCheckName("叶缨");
		invoiceHead.setPayeeName("史佳萍");

		invoiceHead.setOrgTaxexceed(new BigDecimal(1000));
		if(invoiceHead.getMergeAmt()==null||invoiceHead.getMergeAmt().compareTo(BigDecimal.ZERO)<=0){
			invoiceHead.setMergeAmt(new BigDecimal(1000));
		}
		if("1".equals(invoiceHead.getInvoiceType())){
			//专票
			invoiceHead.setOrgTaxexceed(new BigDecimal(1000));
			invoiceHead.setMergeAmt(new BigDecimal(1000));
		}else{
			invoiceHead.setOrgTaxexceed(new BigDecimal(10));
			invoiceHead.setMergeAmt(new BigDecimal(10));
		}
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String docDate = simpleDateFormat.format(new Date());
        invoiceHead.setDocDate(docDate);
		if(invoiceHead.getDocStatus().equals("2")){
			invoiceHead.setBillDate(docDate);
		}
		int i = (int)(Math.random()*900 + 100);
		String myStr = Integer.toString(i);
		System.out.println(myStr);
         String docNum = ""+(new Date().getTime())+myStr; 
		
		
		
//		String docNum = null; //我改的,让产生的新单据号和源单据号保持一致 
//		if(invoiceHead.getOriginalNos()!=null&&invoiceHead.getOriginalNos().substring(0, 1).equals("'")) {
//			docNum = invoiceHead.getOriginalNos().substring(1, invoiceHead.getOriginalNos().length()-1); //我改的,让产生的新单据号和源单据号保持一致
//		}else {
//			docNum = invoiceHead.getOriginalNos(); //我改的,让产生的新单据号和源单据号保持一致
//		}
//		
		
		
//		String docNum = invoiceHead.getOriginalNos(); //我改的,让产生的新单据号和源单据号保持一致
		if(!StringUtils.isEmpty(invoiceHead.getDocNum())  ){//修改
			//查看当前单据状态
			String stat= getDocStatusByDocNum(invoiceHead.getDocNum());
			if("1".equals(stat)||"3".equals(stat)){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg","当前单据状态不允许该操作！：单据号:"+invoiceHead.getDocNum());
				jsonObject.put("success",false);
				throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
			}

			manager.update("invoice_head.update", invoiceHead);
//manager.delete("invoice_original_line.del", invoiceHead);
			manager.delete("invoice_mergef_line.del", invoiceHead);
//			manager.delete("invoice_merges_line.del", invoiceHead);
			manager.delete("invoice_split_line.del", invoiceHead);
		}else{//插入
			//保存客户信息
			saveCustomer(invoiceHead);
			//更新原始单据状态
			updateOriginalStatus(invoiceHead.getOriginalNos(),"0");
			invoiceHead.setDocNum(docNum);
			invoiceHead.setMandt("800");
			invoiceHead.setOrgId("800");
			invoiceHead.setOrgControltax("0");
			manager.insert("invoice_head.create", invoiceHead);
			invoiceHead.setDocNum(docNum);
			List<InvoiceOriginalLine> originalLines = invoiceHead.getInvoiceOriginalLines();
			if(originalLines!=null&&originalLines.size()>0)  {
				for(InvoiceOriginalLine type:originalLines) {
					type.setDocNum(invoiceHead.getDocNum());
					if(type.getQuantity()==null){
						type.setQuantity(BigDecimal.ZERO);
					}
					if(type.getZpriceHsj()==null){
						type.setZpriceHsj(BigDecimal.ZERO);
					}
					if(type.getZpriceWsj()==null){
						type.setZpriceWsj(BigDecimal.ZERO);
					}
//					if(StringUtils.isEmpty(type.getTaxCatecode())) {
//						//先从缓存中匹配税收分类编码
//						String taxCatecode = (String) redis.get("taxCatecode:"+type.getItemName());
//						if(StringUtils.isEmpty(taxCatecode)){
//							//若没有匹配成功，则调用接口获取
//							String accessToken = (String) redis.get("tz-accessToken");
//							String code = new TestOpenApiV3().getCode(type.getItemName(),type.getTaxRate(), accessToken);
//							type.setTaxCatecode(code);
//							redis.set("taxCatecode:"+type.getItemName(),code);
//						}else{
//							type.setTaxCatecode(taxCatecode);
//						}
//
//					}
//					else{
//						//修正税收分类编码
//						redis.set("taxCatecode:"+type.getItemName(),type.getTaxCatecode());
//					}
					manager.insert("invoice_original_line.create", type);
				}
			}
		}



		List<InvoiceMergefLine> mergefLines = invoiceHead.getInvoiceMergefLines();
		if(mergefLines!=null&&mergefLines.size()>0) {
			for(InvoiceMergefLine type:mergefLines) {
				type.setDocNum(invoiceHead.getDocNum());
				
				if(type.getQuantity()==null){
					type.setQuantity(BigDecimal.ZERO);
				}
				if(type.getZpriceHsj()==null){
					type.setZpriceHsj(BigDecimal.ZERO);
				}
				if(type.getZpriceWsj()==null){
					type.setZpriceWsj(BigDecimal.ZERO);
				}
				/**
				 * 更新客户化物料
				 */
				
				getCM(type,InvoiceMergefLine.class,type.getItemNumOrg(),invoiceHead.getCustTaxcode());
				if(StringUtils.isEmpty(type.getTaxCatecode())) {
					//先从缓存中匹配税收分类编码
				}else{
					//修正税收分类编码
					redis.set("taxCatecode:"+type.getItemName(),type.getTaxCatecode());
				}
				
				manager.insert("invoice_mergef_line.create", type);
			}
		}

//		List<InvoiceMergesLine> mergesLines = invoiceHead.getInvoiceMergesLines();
//		if(mergesLines!=null&&mergesLines.size()>0) {
//			for(InvoiceMergesLine img:mergesLines) {
//				img.setDocNum(invoiceHead.getDocNum());
//				manager.insert("invoice_merges_line.create", img);
//			}
//		}
         
		
		System.out.println("执行到行了");
//		开票分组
		List<InvoiceSplitLine> splitLines = invoiceHead.getInvoiceSplitLines();
		if(splitLines!=null&&splitLines.size()>0) {
			for(InvoiceSplitLine news:splitLines) {
				news.setDocNum(invoiceHead.getDocNum());
				if(news.getQuantity()==null){
					news.setQuantity(BigDecimal.ZERO);
				}
				if(news.getZpriceHsj()==null){
					news.setZpriceHsj(BigDecimal.ZERO);
				}
				if(news.getZpriceWsj()==null){
					news.setZpriceWsj(BigDecimal.ZERO);
				}
				if(StringUtils.isEmpty(news.getGroupStatus())){
					news.setGroupStatus(invoiceHead.getDocStatus());

				}

				if(StringUtils.isEmpty(news.getDayin())){
					news.setDayin("0");

				}

				if(invoiceHead.getDocStatus().equals("1")){
					news.setBillDate(docDate);
					if(news.getGroupStatus().equals("0")){
						news.setGroupStatus("1");
					}
				}

				/**
				 * 原值=净值 处理
				 */

				news.setZpriceWsy(news.getZpriceWsj());
				news.setZpriceHsy(news.getZpriceHsj());
				news.setZpriceWsy(news.getZpriceWsj());
				news.setZamountSey(news.getZamountSej());
				news.setZamountWsy(news.getZamountWsj());
				news.setZamountHsy(news.getZamountHsj());

				manager.insert("invoice_split_line.create", news);
			}
		}

//		if(noCode){
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("msg","税收分类编码不全，请查看！：单据号:"+invoiceHead.getDocNum());
//			jsonObject.put("success",false);
//			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
//		}
		return 1;
		
	}


	@Override
	public Integer save(InvoiceHead invoiceHead) {
		return null;
	}

	@Override
	public InvoiceHead queryByEntity(@RequestBody InvoiceHead invoiceHead){
	             return (InvoiceHead)manager.query("invoice_head.query", invoiceHead);
	}
	@Override
	public List<InvoiceHead> queryList(@RequestBody InvoiceHead invoiceHead){
		List<InvoiceHead> list =  (List<InvoiceHead>)manager.list("invoice_head.queryList", invoiceHead);
		return list;
	}

	@Override
    public Pages<List<InvoiceHead>> pages(@RequestBody InvoiceHead invoiceHead, Integer pageSize, Integer pageNum){
        EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceHead>>) manager.pages("invoice_head.page", invoiceHead, page);
	
	}
	/**
    @Override
    public Pages<List<InvoiceHead>> pages(@RequestBody InvoiceHeadPojo invoiceHeadPojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceHead>>) manager.pages("invoice_head.page", invoiceHeadPojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody InvoiceHead invoiceHead) {
		return manager.update("invoice_head.changeDelStateById", invoiceHead);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody InvoiceHead invoiceHeadPojo) {
		return manager.update("invoice_head.changeDelStateByIds", invoiceHeadPojo);
	}

	/**
	 *根据客户税号查看客户信息是否已存在
	 */
    private void  saveCustomer(InvoiceHead invoiceHead){
    	if(invoiceHead!=null&&!StringUtils.isEmpty(invoiceHead.getCustTaxcode())){
    		Customer customer = new Customer();
    		customer.setTaxNo(invoiceHead.getCustTaxcode());
			Customer c = (Customer)manager.query("customer.query", customer);
			if(c==null||StringUtils.isEmpty(c.getTaxNo())){
				//不存在则保存
				customer.setCustomerName(invoiceHead.getCustName());
				customer.setCustomerNo(invoiceHead.getCustIdBill());
				manager.insert("customer.create", customer);
			}
		}

	}

	/**
	 * 根据原始单据号修改原始单据状态为已引入
	 * 防止多次引入造成重复开票
	 */
	private void updateOriginalStatus(String originalNos,String status){
		if(!StringUtils.isEmpty(originalNos)&&!StringUtils.isEmpty(status)){
			InvoiceHead invoiceHead = new InvoiceHead();
			invoiceHead.setDocStatus(status);
			invoiceHead.setOriginalNos(originalNos);
			manager.update("original_head.changeDelStateByIds", invoiceHead);
		}
	}

	/**
	 * 获取客户化物料并赋值
	 */

	private <T> void getCM(Object o, Class<T> clz,String itemCodeOld,String taxNo){
		if(o!=null&&!StringUtils.isEmpty(itemCodeOld)&&!StringUtils.isEmpty(taxNo)){
			/**
			 * 根据原物料编码、税号查询是否有客户化物料
			 */
			CustomizedMaterials c = new CustomizedMaterials();
			c.setItemNumOriginal(itemCodeOld);
			c.setTaxNo(taxNo);
			CustomizedMaterials customizedMaterials = (CustomizedMaterials)manager.query("customized_materials.query", c);
			if(customizedMaterials!=null&&!StringUtils.isEmpty(customizedMaterials.getTaxNo())){
				if(clz.isInstance(o)){
					clz.cast(o);
					try {
						Method in =  clz.getDeclaredMethod("setItemName",String.class);
						in.invoke(clz.cast(o),customizedMaterials.getItemName());
						Method is =  clz.getDeclaredMethod("setItemSpec",String.class);
						is.invoke(clz.cast(o),customizedMaterials.getItemSpec());
						Method un =  clz.getDeclaredMethod("setUnitName",String.class);
						un.invoke(clz.cast(o),customizedMaterials.getUnitName());
					}catch (Exception e){
e.printStackTrace();
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("msg","客户化物料匹配参数异常！");
						jsonObject.put("success",false);
						throw  new BusinessException(ResultCode.PARAM_IS_INVALID,jsonObject);
					}

				}
			}

		}else {

		}


	}

	@Override
	@Transactional
	public boolean del(InvoiceHead invoiceHead) {

		InvoiceOriginalLine line = new InvoiceOriginalLine();
		line.setDocNum(invoiceHead.getDocNum());
		String s = getDocStatusByDocNum(invoiceHead.getDocNum());
		if(!"0".equals(s)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","当前单据状态不允许删除：单据号:"+invoiceHead.getDocNum());
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}
		List<InvoiceOriginalLine> list  = (List<InvoiceOriginalLine>)manager.list("invoice_original_line.queryList", line);
		if(list!=null&&list.size()>0){
			String ids = "";
			for(InvoiceOriginalLine l :list){
				ids=ids+"'"+l.getDocOrignum()+"',";
			}
			if(ids.length()>0){
				ids=ids.substring(0,ids.length()-1);
			}
			updateOriginalStatus(ids,"-1");
			manager.delete("invoice_head.del", invoiceHead);
			return true;
		}

		return false;
	}

	
	/**
	 * 查询单据状态
	 */

	private  String getDocStatusByDocNum(String docNum){
		InvoiceHead invoiceHead = new InvoiceHead();
		invoiceHead.setDocNum(docNum);
		invoiceHead =  (InvoiceHead)manager.query("invoice_head.query", invoiceHead);
		if(invoiceHead!=null&&!StringUtils.isEmpty(invoiceHead.getDocStatus())){
			return invoiceHead.getDocStatus();
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","查询单据状态异常：单据号:"+docNum);
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}
	}


	private InvoiceHead getHead(String docNum) {
		InvoiceHead head = new InvoiceHead();
		head.setDocNum(docNum);
		InvoiceHead ih = (InvoiceHead)this.manager.query("invoice_head.queryHead", head);
		return ih;
	}

	private List<InvoiceSplitLine> getBodys(String docNum) {
		InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
		invoiceSplitLine.setDocNum(docNum);
		List<InvoiceSplitLine> bodys = (List<InvoiceSplitLine>) manager.list("invoice_split_line.queryList", invoiceSplitLine);
		return bodys;
	}

	@Transactional
	public Object invoice(@RequestBody InvoiceHeadDto invoiceHeadDto) {
		validateHead(invoiceHeadDto);
		log.info("验证通过============");
		InvoiceHead invoiceHead = (InvoiceHead) POJOConvertUtil.getConvertMapper().map(invoiceHeadDto, InvoiceHead.class);
		if ("1".equals(invoiceHeadDto.getReopen())) {
			log.info("重开==========================");
			InvoiceHead gh = getHead(invoiceHeadDto.getDocNum());
			if (gh == null) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "未找到原开票信息！");
				jsonObject.put("success", Boolean.valueOf(false));
				throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
			}
			if ("1".equals(gh.getDocStatus())) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg", "该单据正在提交开票不能进行重开操作！");
				jsonObject.put("success", Boolean.valueOf(false));
				throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
			}
			int ls = (int)(Math.random() * 90000.0D + 10000.0D);
			String myStr_ls = Integer.toString(ls);
			invoiceHead.setAttribute2(invoiceHead.getDocNum() + myStr_ls);
			log.info("重开修改主表编码");
			this.manager.update("invoice_head.reopen", (IPO)invoiceHead);
		}
		InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
		invoiceSplitLine.setDocNum(invoiceHead.getDocNum());
		List<InvoiceSplitLine> splitLines = (List<InvoiceSplitLine>) manager.list("invoice_split_line.queryListByDocNum", invoiceSplitLine);
		if (splitLines != null && splitLines.size() > 0) {
			log.info("已存在单据号========================");
			InvoiceHead head = new InvoiceHead();
			head.setDocNum(invoiceHead.getDocNum());
			InvoiceHead ih = (InvoiceHead)this.manager.query("invoice_head.queryHead", head);
			if (ih == null || StringUtils.isEmpty(ih.getDocNum())) {
				log.info("表头无信息======================");
				JSONObject jSONObject = new JSONObject();
				jSONObject.put("msg", "查询数据库中表头信息异常！");
				jSONObject.put("success", Boolean.valueOf(false));
				throw new BusinessException(ResultCode.DATA_IS_WRONG, jSONObject);
			}
			String gs = ih.getDocStatus();
			if ("2".equals(gs)) {
				log.info("已存在单据，并已成功开票===================");
				InvoiceReVo reVo = (InvoiceReVo)POJOConvertUtil.getConvertMapper().map(ih, InvoiceReVo.class);
				reVo.setMsg("金税开票成功");
				log.info("查询pdf===========");
				String url = (String)this.redis.get(reVo.getDocNum());
				reVo.setPdfUrl(url);
				return reVo;
			}
			if ("1".equals(gs)) {

			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "开票单据编号已存在，请勿重复开票");
			jsonObject.put("success", Boolean.valueOf(false));
			throw new BusinessException(ResultCode.DATA_IS_WRONG, jsonObject);
		}
		try {
			log.info("不存在单据，保存开票数据");
			invoiceHead.setDocStatus("1");
			saveInvoice(invoiceHead);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "开票信息保存成功!");
			jsonObject.put("success", true);
			return jsonObject;
//			VInvoiceHead vh = new VInvoiceHead();
//			vh.setDOC_NUM(invoiceHead.getDocNum());
//			VInvoiceHead vInvoiceHead = (VInvoiceHead)this.manager.query("invoice_head.queryView", vh);
//			doKp(vInvoiceHead, "8100");
		} catch (Exception e) {
			log.info("保存开票数据异常{}",e.getMessage());
			e.printStackTrace();
			log.error(e.getMessage());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "保存开票信息失败！");
			jsonObject.put("success", Boolean.valueOf(false));
			throw new BusinessException(ResultCode.DATA_IS_WRONG, jsonObject);
		}
	}

	private void validateHead(InvoiceHeadDto invoiceHeadDto) {
		Class<InvoiceHeadDto> clazz = InvoiceHeadDto.class;
		Field[] fs1 = clazz.getDeclaredFields();
		if (invoiceHeadDto != null)
			for (Field f : fs1) {
				EntityValidate ev = f.<EntityValidate>getAnnotation(EntityValidate.class);
				if (ev != null &&
						!ev.allowEmpty()) {
					String key = f.getName();
					Object o = null;
					try {
						PropertyDescriptor descriptor = new PropertyDescriptor(key, InvoiceHeadDto.class);
						Method method = descriptor.getReadMethod();
						f.setAccessible(true);
						o = method.invoke(invoiceHeadDto, new Object[0]);
					} catch (IntrospectionException |IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
						e.printStackTrace();
					}
					if (o == null) {
						ExcelCell ec = f.<ExcelCell>getAnnotation(ExcelCell.class);
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("msg", ec.name() + "不能为空！");
						jsonObject.put("success", Boolean.valueOf(false));
						throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
					}
					if (o instanceof String) {
						String v = (String)o;
						if (v.length() == 0) {
							ExcelCell ec = f.<ExcelCell>getAnnotation(ExcelCell.class);
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("msg", ec.name() + "不能为空！");
							jsonObject.put("success", Boolean.valueOf(false));
							throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
						}
					}
				}
			}
		if (invoiceHeadDto.getInvoiceSplitLines() != null && invoiceHeadDto.getInvoiceSplitLines().size() > 0) {
			List<InvoiceSplitLineDto> isds = invoiceHeadDto.getInvoiceSplitLines();
			for (InvoiceSplitLineDto isd : isds) {
				Class<InvoiceSplitLineDto> clazz2 = InvoiceSplitLineDto.class;
				Field[] fs2 = clazz2.getDeclaredFields();
				for (Field f : fs2) {
					EntityValidate ev = f.<EntityValidate>getAnnotation(EntityValidate.class);
					if (ev != null &&
							!ev.allowEmpty()) {
						String key = f.getName();
						Object o = null;
						try {
							PropertyDescriptor descriptor = new PropertyDescriptor(key, InvoiceSplitLineDto.class);
							Method method = descriptor.getReadMethod();
							f.setAccessible(true);
							o = method.invoke(isd, new Object[0]);
						} catch (IntrospectionException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
							e.printStackTrace();
						}
						if (o == null) {
							ExcelCell ec = f.<ExcelCell>getAnnotation(ExcelCell.class);
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("msg", ec.name() + "不能为空！");
							jsonObject.put("success", Boolean.valueOf(false));
							throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
						}
						if (o instanceof String) {
							String v = (String)o;
							if (v.length() == 0) {
								ExcelCell ec = f.<ExcelCell>getAnnotation(ExcelCell.class);
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("msg", ec.name() + "不能为空！");
								jsonObject.put("success", Boolean.valueOf(false));
								throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
							}
						}
					}
				}
			}
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "未找到发票明细！");
			jsonObject.put("success", Boolean.valueOf(false));
			throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
		}
		if (invoiceHeadDto == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", "未找到开票信息！");
			jsonObject.put("success", Boolean.valueOf(false));
			throw new BusinessException(ResultCode.PARAM_IS_INVALID, jsonObject);
		}
	}


	@Transactional
	public void saveInvoice(InvoiceHead invoiceHead) {
//		if (StringUtils.isNotEmpty(this.dev_or_prod) && this.dev_or_prod.equals("dev"))
//			if ("4".equals(invoiceHead.getInvoiceType())) {
//				invoiceHead.setOrgName("百旺电子测试1");
//				invoiceHead.setOrgTaxcode("110109500321654");
//			} else {
//				invoiceHead.setOrgName("百旺电子测试1");
//				invoiceHead.setOrgTaxcode("110109500321654");
//			}
		log.info("保存发票信息=======");
		String docNum = invoiceHead.getDocNum();
		int ls = (int) (Math.random() * 900.0D + 100.0D);
		String myStr_ls = Integer.toString(ls);
		invoiceHead.setAttribute1(myStr_ls);
		invoiceHead.setOrgTaxexceed(new BigDecimal(100));
		if (invoiceHead.getMergeAmt() == null || invoiceHead.getMergeAmt().compareTo(BigDecimal.ZERO) <= 0){
			invoiceHead.setMergeAmt(new BigDecimal(100));}
		log.info("保存发票信息=======1");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String docDate = simpleDateFormat.format(new Date());
		invoiceHead.setDocDate(docDate);
		if (invoiceHead.getDocStatus().equals("2")) {
			invoiceHead.setBillDate(docDate);
		}
		invoiceHead.setMandt("800");
		invoiceHead.setOrgId("800");
		invoiceHead.setOrgControltax("0");
		invoiceHead.setDocStatus("0");
		/**
		 * 根据客户税号补充客户信息
		 */
		CustomerMsg cm = new CustomerMsg();
		cm.setCustTaxcode(invoiceHead.getCustTaxcode());
		cm = (CustomerMsg) manager.query("invoice_head.queryByCode",cm);
		if(cm==null||StringUtils.isEmpty(cm.getCustTaxcode())){
			invoiceHead.setCustBankaccount(cm.getCustBankaccount());
			invoiceHead.setCustBankname(cm.getCustBankname());
			invoiceHead.setCustTelephone(cm.getCustTelephone());
			invoiceHead.setCustAddress(cm.getCustAddress());
		}
		log.info("保存发票信息=======2");
		this.manager.insert("invoice_head.create", (IPO) invoiceHead);
		log.info("保存发票信息=======3");
		List<InvoiceSplitLine> sl = invoiceHead.getInvoiceSplitLines();
		if (sl != null && sl.size() > 0){
			log.info("保存发票信息=======4");
			int line= 1001;
			for (InvoiceSplitLine news : sl) {

				log.info("保存发票信息=======5");
				news.setDocLine(""+line);
				news.setGroupNum("101");
				news.setMandt("800");
				news.setDocNum(docNum);
				if (StringUtils.isEmpty(news.getGroupStatus()))
					news.setGroupStatus(invoiceHead.getDocStatus());
				if (invoiceHead.getDocStatus().equals("1")) {
					news.setBillDate(docDate);
					if (news.getGroupStatus().equals("0"))
						news.setGroupStatus("1");
				}
				if (news.getZpriceWsy() != null) {
					news.setZpriceWsy(news.getZpriceWsy());
				} else {
					news.setZpriceWsy(news.getZpriceWsj());
				}
				if (news.getZpriceHsy() != null) {
					news.setZpriceHsy(news.getZpriceHsy());
				} else {
					news.setZpriceHsy(news.getZpriceHsj());
				}
				if (news.getZpriceWsy() != null) {
					news.setZpriceWsy(news.getZpriceWsy());
				} else {
					news.setZpriceWsy(news.getZpriceWsj());
				}
				if (news.getZamountSey() != null) {
					news.setZamountSey(news.getZamountSey());
				} else {
					news.setZamountSey(news.getZamountSej());
				}
				if (news.getZamountWsy() != null) {
					news.setZamountWsy(news.getZamountWsy());
				} else {
					news.setZamountWsy(news.getZamountWsj());
				}
				if (news.getZamountHsy() != null) {
					news.setZamountHsy(news.getZamountHsy());
				} else {
					news.setZamountHsy(news.getZamountHsj());
				}
				log.info("保存发票信息=======6");
				this.manager.insert("invoice_split_line.create", (IPO) news);
				log.info("保存发票信息=======7");
				line++;
			}
	}
	}


	@Override
	@Transactional
	public Integer insert(InvoiceHead invoiceHead) {
		
//		检查数据库中是否有重复的单据
		InvoiceHead query = (InvoiceHead) this.manager.query("original_head.query", invoiceHead.getDocNum());
		if(query!=null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","请勿重复插入!,已存在单据号："+invoiceHead.getDocNum());
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}
		
		invoiceHead.setDocStatus("-1");
		List<InvoiceOriginalLine> lines = invoiceHead.getInvoiceOriginalLines();
		if(lines==null||lines.size()==0) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","行数据不能为空");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}
		
//		插入头数据
		this.manager.insert("original_head.create", (IPO) invoiceHead);
		int insert=0;
		for (InvoiceOriginalLine invoiceOriginalLine : lines) {
			insert = manager.insert("original_body.create",invoiceOriginalLine);
		}
		return insert;
	}


	

}