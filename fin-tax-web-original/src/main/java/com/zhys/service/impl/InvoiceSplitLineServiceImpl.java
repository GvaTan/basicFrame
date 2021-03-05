package com.zhys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhys.exception.BusinessException;
import com.zhys.invoice.po.InvoiceHead;
import com.zhys.invoice.po.InvoiceOriginalLine;
import com.zhys.invoice.po.InvoiceSplitLine;
import com.zhys.result.ResultCode;
import com.zhys.service.SQLManager;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.cmp.kit.EasyPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhys.base.BaseApiService;
import com.zhys.base.ResponseBase;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import com.zhys.service.InvoiceSplitLineService;

@Slf4j
@RestController
public class InvoiceSplitLineServiceImpl extends BaseApiService implements InvoiceSplitLineService{

	@Autowired
	private SQLManager manager;


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
	 public Pages<List<InvoiceSplitLine>> pages(@RequestBody InvoiceSplitLinePojo invoiceSplitLinePojo, Integer pageSize, Integer pageNum){
	 EasyPage page = new EasyPage();
	 page.pageNum(pageNum);
	 page.pageSize(pageSize);
	 return (Pages<List<InvoiceSplitLine>>) manager.pages("invoice_split_line.page", invoiceSplitLinePojo, page);

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

	@Override
	public Integer cancelCommit(@RequestBody InvoiceSplitLine invoiceSplitLine) {
//		String docStatus = getDocStatusByDocNum(invoiceSplitLine.getDocNum());
//		if(!docStatus.equals("1")){
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("msg","该单据状态不能进行撤销提交操作,请先同步！");
//			jsonObject.put("success",false);
//			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
//		}
		manager.update("invoice_split_line.cancelCommit", invoiceSplitLine);
		return 1;
	}

	@Override
	@Transactional
	public Integer abolishAll(InvoiceSplitLine invoiceSplitLine) {
		String docStatus = getDocStatusByDocNum(invoiceSplitLine.getDocNum());
		if(!docStatus.equals("2")){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","该单据状态不能进行整单作废操作！");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}
		updateOriginalStatus(invoiceSplitLine,"0");
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cancleDate = simpleDateFormat.format(new Date());
		invoiceSplitLine.setCancelDate(cancleDate);
		manager.update("invoice_split_line.abolishAll", invoiceSplitLine);
////		自己添加的
//		InvoiceHead invoiceHead = new InvoiceHead();
//		invoiceHead.setDocNum(invoiceSplitLine.getDocNum());
//		invoiceHead.setDocStatus("3");
//		manager.update("invoice_head.changeDocStatusById", invoiceHead);
		return 1;
	}

	@Override
	@Transactional
	public Integer abolishOne(InvoiceSplitLine invoiceSplitLine) {


		if(!StringUtils.isEmpty(invoiceSplitLine.getDocNum())){

			if(getGroupStatus(invoiceSplitLine.getDocNum(),invoiceSplitLine.getGroupNum(),"2")){
				SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String cancleDate = simpleDateFormat.format(new Date());
				invoiceSplitLine.setCancelDate(cancleDate);
				manager.update("invoice_split_line.abolishOne", invoiceSplitLine);
			}
//				if(!groupStatus.equals("2")){
//					JSONObject jsonObject = new JSONObject();
//					jsonObject.put("msg","该分组状态不能进行分组作废操作！");
//					jsonObject.put("success",false);
//					throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
//				}


		}

		if(!existGroupStatus(invoiceSplitLine.getDocNum(),"2")){
			InvoiceHead invoiceHead = new InvoiceHead();
			invoiceHead.setDocNum(invoiceSplitLine.getDocNum());
			invoiceHead.setDocStatus("3");
			manager.update("invoice_head.changeDocStatusById", invoiceHead);
		}
		return 1;
	}

	@Override
	@Transactional
	public Integer cancelAbolishAll(InvoiceSplitLine invoiceSplitLine) {
		String docStatus = getDocStatusByDocNum(invoiceSplitLine.getDocNum());
		if(!docStatus.equals("3")){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","该单据状态不能进行撤销整单作废操作！");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.STATUS_ERRO,jsonObject);
		}

		try {
			updateOriginalStatus(invoiceSplitLine,"0");
			manager.update("invoice_split_line.cancelAbolishAll", invoiceSplitLine);
		}catch (Exception e){

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","撤销整单作废失败，单据号：:"+invoiceSplitLine.getDocNum());
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
		}
		return 1;

	}

	@Override
	public Integer cancelAbolishOne(InvoiceSplitLine invoiceSplitLine) {
		if(getGroupStatus(invoiceSplitLine.getDocNum(),invoiceSplitLine.getGroupNum(),"3")){
			try {
				manager.update("invoice_split_line.cancelAbolishOne", invoiceSplitLine);
			}catch (Exception e){

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("msg","撤销作废失败，单据号：:"+invoiceSplitLine.getDocNum());
				jsonObject.put("success",false);
				throw  new BusinessException(ResultCode.DATA_IS_WRONG,jsonObject);
			}
			return 1;

		}

		return null;
	}


	/**
	 * 根据原始单据号修改原始单据状态为已引入
	 * 防止多次引入造成重复开票
	 */
	private void updateOriginalStatus(InvoiceSplitLine invoiceOriginalLine,String status){
		if(invoiceOriginalLine!=null&&!StringUtils.isEmpty(invoiceOriginalLine.getDocNum())){
			//根据单据号查询所有该单据号下原始单据
			List<InvoiceOriginalLine> list = (List<InvoiceOriginalLine>)manager.list("invoice_original_line.queryList", invoiceOriginalLine);
			String originalNos = "";
			if(!CollectionUtils.isEmpty(list)){
				for(InvoiceOriginalLine orginal:list){
					originalNos = originalNos+"'"+orginal.getDocOrignum()+"',";
				}
				if(originalNos.length()>1){
					originalNos = originalNos.substring(0,originalNos.length()-1);
					if(!StringUtils.isEmpty(status)){
						InvoiceHead invoiceHead = new InvoiceHead();
						invoiceHead.setDocStatus(status);
						invoiceHead.setOriginalNos(originalNos);
						manager.update("original_head.changeDelStateByIds", invoiceHead);
					}
				}
			}


		}
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