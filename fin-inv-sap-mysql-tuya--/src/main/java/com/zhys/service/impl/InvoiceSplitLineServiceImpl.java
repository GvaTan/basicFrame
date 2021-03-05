package com.zhys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lycheeframework.core.cmp.kit.EasyPage;
import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.base.BaseApiService;
import com.zhys.excel.ExcelCell;
import com.zhys.excel.ImportExcelUtil;
import com.zhys.exception.BusinessException;
import com.zhys.invoice.po.InvoiceHead;
import com.zhys.invoice.po.InvoiceSplitLine;
import com.zhys.po.Express;
import com.zhys.po.ExpressExcel;
import com.zhys.redis.RedisUtils;
import com.zhys.result.ResultCode;
import com.zhys.service.InvoiceSplitLineService;
import com.zhys.util.SQLManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class InvoiceSplitLineServiceImpl extends BaseApiService implements InvoiceSplitLineService {

	@Autowired
	private SQLManager manager;
	@Autowired
	private RedisUtils redis;

	@Override
	public Object hc_express(List<Express> list){
		try {
			if(list!=null&&list.size()>0){
				for(Express express:list){
					manager.update("invoice_split_line.hc_express",express);
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg","成功！");
				jsonObject.put("success",true);
				return jsonObject;
			}else{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg","参数必传。");
				jsonObject.put("success",false);
				throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
			}
		}catch (Exception e){
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","运行出错");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.SYSTEM_INNER_ERROR,jsonObject);
		}

	}

	@Override
	public Object upload(MultipartFile file) {
//		File f1 = new File("src"+File.separator+"+test"+File.separator+""+file.getOriginalFilename());

		try {
//			FileUtils.copyInputStreamToFile(file.getInputStream(), f1);
			    	Map<String,String> map = new HashMap<>();
    	Class clazz = ExpressExcel.class;
    	Field[] fs = clazz.getDeclaredFields();
    	if(fs != null&&fs.length>0) {
    		for(Field f:fs) {
    			ExcelCell ec = f.getAnnotation(ExcelCell.class);
    			if(ec!=null) {
    				map.put(ec.name(), f.getName());
    			}

    		}
    	}

			List<ExpressExcel> etps = ImportExcelUtil.readExcel(map, ExpressExcel.class,file.getInputStream(),"test.xlsx",0);
			etps.forEach(ee -> {
                 if(ee!=null&&StringUtils.isNotEmpty(ee.getExid())&&StringUtils.isNotEmpty(ee.getExnum())){
					 Express express = new Express();
					 express.setExid(ee.getExid());
					 express.setExnum(ee.getExnum());
					 manager.update("invoice_split_line.hc_exno",express);
				 }
			});
		} catch (IOException e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","运行出错");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.SYSTEM_INNER_ERROR,jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg","成功！");
		jsonObject.put("success",true);
		return jsonObject;
	}

	@Override
	public Object hf(String doc_num, String group_num) {
		com.zhys.po.InvoiceSplitLine splitLine = new com.zhys.po.InvoiceSplitLine();
		splitLine.setDocNum(doc_num);
		splitLine.setGroupNum(group_num);
		log.info("重开发票-恢复单据:"+doc_num + group_num);
		manager.update("invoice_split_line.hf",splitLine);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg","恢复单据成功，正在等待重开！");
		jsonObject.put("success",true);
		return jsonObject;
	}


	@Override
	public Integer save(@RequestBody InvoiceSplitLine invoiceSplitLine){
		InvoiceSplitLine c = (InvoiceSplitLine) manager.query("invoice_split_line.query",invoiceSplitLine);
		if(c != null ){//修改
				manager.update("invoice_split_line.update", invoiceSplitLine);
		}else{//插入
			    manager.insert("invoice_split_line.create", invoiceSplitLine);
		}
   return 1;
	}


	@Override
	public InvoiceSplitLine queryByEntity(@RequestBody InvoiceSplitLine invoiceSplitLine){
		return (InvoiceSplitLine)manager.query("invoice_split_line.query", invoiceSplitLine);
	}
	@Override
	public List<InvoiceSplitLine> queryList(@RequestBody InvoiceSplitLine invoiceSplitLine){
		return (List<InvoiceSplitLine>)manager.list("invoice_split_line.queryList", invoiceSplitLine);
	}
	@Override
	public Pages<List<InvoiceSplitLine>> pages(@RequestBody InvoiceSplitLine invoiceSplitLine, Integer pageSize, Integer pageNum){
		EasyPage page = new EasyPage();
		page.pageNum(pageNum);
		page.pageSize(pageSize);
		return (Pages<List<InvoiceSplitLine>>) manager.pages("invoice_split_line.page", invoiceSplitLine, page);

	}
	/**
	 @Override
	 public Pages<List<InvoiceSplitLineDto>> pages(@RequestBody InvoiceSplitLinePojo invoiceSplitLinePojo, Integer pageSize, Integer pageNum){
	 EasyPage page = new EasyPage();
	 page.pageNum(pageNum);
	 page.pageSize(pageSize);
	 return (Pages<List<InvoiceSplitLineDto>>) manager.pages("invoice_split_line.page", invoiceSplitLinePojo, page);

	 }**/

	@Override
	public Integer changeDelStateById(@RequestBody InvoiceSplitLine invoiceSplitLine) {
		manager.update("invoice_split_line.changeDelStateById", invoiceSplitLine);
		return 1;
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody InvoiceSplitLine invoiceSplitLinePojo) {
		manager.update("invoice_split_line.changeDelStateByIds", invoiceSplitLinePojo);
		return 1;
	}

	/**
	 * 通过单据号查询发票明细信息
	 */
	private List<InvoiceSplitLine> getBodys(String docNum){
		InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
		invoiceSplitLine.setDocNum(docNum);
		List<InvoiceSplitLine>  bodys = (List<InvoiceSplitLine>)manager.list("invoice_split_line.queryList", invoiceSplitLine);
		return bodys;
	}

	@Override
	public Object cancelCommit(@RequestBody InvoiceSplitLine invoiceSplitLine) {

		List<InvoiceSplitLine> splitLines =  getBodys(invoiceSplitLine.getDocNum());
		if(splitLines==null||splitLines.size()==0){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","未查询到该单据！");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.PARAM_IS_INVALID,jsonObject);
		}
		String fpdm = splitLines.get(0).getGoldtaxCode();
		if("1".equals(fpdm)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","请手动操作中航开票服务，点击【断点回传】按钮，同步成功后再进行撤销。");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
		}

		String docStatus = getDocStatusByDocNum(invoiceSplitLine.getDocNum());
		if(!docStatus.equals("1")){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","该单据状态不能进行撤销提交操作");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}

		try {
			manager.update("invoice_split_line.cancelCommit", invoiceSplitLine);
		}catch (Exception e){

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","撤销提交失败，单据号：:"+invoiceSplitLine.getDocNum());
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg","撤销提交成功！");
		jsonObject.put("success",true);
		return jsonObject;
	}

	private void validateServiceStart(String key){
		Object v = redis.get(key);
		if(v!=null){
			String val = ""+v;
			if(!"1".equals(val)){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg","请确认中航开票服务开启后重试");
				jsonObject.put("success",false);
				throw  new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR,jsonObject);
			}
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","缓存服务异常,开票软件未开启。");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR,jsonObject);
		}
	}

	@Override
	public Object abolishAll(InvoiceSplitLine invoiceSplitLine) {
		validateServiceStart("zuofei");
		String docStatus = getDocStatusByDocNum(invoiceSplitLine.getDocNum());
		if("9".equals(docStatus)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","作废成功！");
			jsonObject.put("success",true);
			return jsonObject;
		}
		else if("2".equals(docStatus)){
			SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String cancleDate = simpleDateFormat.format(new Date());
			invoiceSplitLine.setCancelDate(cancleDate);
			manager.update("invoice_split_line.abolishAll", invoiceSplitLine);
		}
		else if(!docStatus.equals("3")){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","该单据状态不能进行作废操作！");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}

		return "timerTask";
	}

	@Override
	public Object timerTask(String docNum){
		InvoiceHead ihead = null;
		for(int i=0;i<15;i++){
			ihead= getHead(docNum);
			if(ihead!=null&&"9".equals(ihead.getDocStatus())){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg","作废成功！");
				jsonObject.put("success",true);
				return jsonObject;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isEmpty(ihead.getAttribute3())){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","错误原因："+ihead.getAttribute3());
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","处理超时，请稍后重试");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
		}
	}


	/**
	 * 通过单据号查询发票表头信息
	 */
	private InvoiceHead getHead(String docNum){
		InvoiceHead head = new InvoiceHead();
		head.setDocNum(docNum);
		InvoiceHead ih = (InvoiceHead)manager.query("invoice_head.queryHead",head);
		return ih;
	}


	@Override
	public Object cancelAbolishAll(InvoiceSplitLine invoiceSplitLine) {
		String docStatus = getDocStatusByDocNum(invoiceSplitLine.getDocNum());
		if(!docStatus.equals("3")){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","该单据状态不能进行撤销作废操作！");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}

		try {
			manager.update("invoice_split_line.cancelAbolishAll", invoiceSplitLine);
		}catch (Exception e){

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","撤销作废失败，单据号：:"+invoiceSplitLine.getDocNum());
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg","撤销作废成功！");
		jsonObject.put("success",true);
		return jsonObject;

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



	/**
	 * 查询某些分组分组状态是否都是某个状态
	 */

	private  boolean getGroupStatus(String docNum,String groupNum,String groupStatus){
		InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
		invoiceSplitLine.setDocNum(docNum);
		invoiceSplitLine.setGroupNum(groupNum);
		List<InvoiceSplitLine> splitLines =  (List<InvoiceSplitLine>)manager.list("invoice_split_line.queryLike", invoiceSplitLine);
		if(!CollectionUtils.isEmpty(splitLines)&&splitLines.size()>0){
			for(InvoiceSplitLine splitLine:splitLines){
				if(!StringUtils.isEmpty(splitLine.getGroupStatus())&&!splitLine.getGroupStatus().equals(groupStatus)){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("msg","查询分组状态异常：单据号:"+docNum);
					jsonObject.put("success",false);
					throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
				}
			}
		}
		return true;
	}

	/**
	 * 查询分组状态是否包含某个状态
	 */

	private  boolean existGroupStatus(String docNum,String groupStatus){
		InvoiceSplitLine invoiceSplitLine = new InvoiceSplitLine();
		invoiceSplitLine.setDocNum(docNum);
		List<InvoiceSplitLine> splitLines =  (List<InvoiceSplitLine>)manager.list("invoice_split_line.queryList", invoiceSplitLine);
		if(!CollectionUtils.isEmpty(splitLines)&&splitLines.size()>0){
			for(InvoiceSplitLine invoiceSplitLine1:splitLines){
				if(!StringUtils.isEmpty(invoiceSplitLine1.getGroupStatus())&&invoiceSplitLine1.getGroupStatus().equals(groupStatus)){
					return true;
				}
			}
			return false;
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","单据明细空，单据号：:"+docNum);
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.RESULE_DATA_NONE,jsonObject);
		}
	}
}