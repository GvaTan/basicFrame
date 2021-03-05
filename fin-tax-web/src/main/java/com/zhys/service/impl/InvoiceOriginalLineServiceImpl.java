package com.zhys.service.impl;

import com.zhys.invoice.po.InvoiceOriginalLine;
import com.zhys.service.SQLManager;
import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.cmp.kit.EasyPage;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhys.base.BaseApiService;
import com.zhys.base.ResponseBase;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import com.zhys.service.InvoiceOriginalLineService;

@Slf4j
@RestController
public class InvoiceOriginalLineServiceImpl extends BaseApiService implements InvoiceOriginalLineService{
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody InvoiceOriginalLine invoiceOriginalLine){
	  InvoiceOriginalLine c = (InvoiceOriginalLine) manager.query("invoice_original_line.query",invoiceOriginalLine);
		if(c != null ){//修改
		  return	manager.update("invoice_original_line.update", invoiceOriginalLine);
		}else{//插入
		  return    manager.insert("invoice_original_line.create", invoiceOriginalLine);
		}
		
	}
	
	
	@Override
	public InvoiceOriginalLine queryByEntity(@RequestBody InvoiceOriginalLine invoiceOriginalLine){
	             return (InvoiceOriginalLine)manager.query("invoice_original_line.query", invoiceOriginalLine);
	}
	@Override
	public List<InvoiceOriginalLine> queryList(@RequestBody InvoiceOriginalLine invoiceOriginalLine){
	          return (List<InvoiceOriginalLine>)manager.list("invoice_original_line.queryList", invoiceOriginalLine);
	}
	@Override
    public Pages<List<InvoiceOriginalLine>> pages(@RequestBody InvoiceOriginalLine invoiceOriginalLine, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceOriginalLine>>) manager.pages("invoice_original_line.page", invoiceOriginalLine, page);
	
	}
	/**
    @Override
    public Pages<List<InvoiceOriginalLine>> pages(@RequestBody InvoiceOriginalLinePojo invoiceOriginalLinePojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceOriginalLine>>) manager.pages("invoice_original_line.page", invoiceOriginalLinePojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody InvoiceOriginalLine invoiceOriginalLine) {
		return manager.update("invoice_original_line.changeDelStateById", invoiceOriginalLine);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody InvoiceOriginalLine invoiceOriginalLinePojo) {
		return manager.update("invoice_original_line.changeDelStateByIds", invoiceOriginalLinePojo);
	}
	
    
}