package com.zhys.service.impl;

import com.zhys.invoice.po.InvoiceMergefLine;
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
import com.zhys.service.InvoiceMergefLineService;

@Slf4j
@RestController
public class InvoiceMergefLineServiceImpl extends BaseApiService implements InvoiceMergefLineService{
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody InvoiceMergefLine invoiceMergefLine){
	  InvoiceMergefLine c = (InvoiceMergefLine) manager.query("invoice_mergef_line.query",invoiceMergefLine);
		if(c != null ){//修改
		  return	manager.update("invoice_mergef_line.update", invoiceMergefLine);
		}else{//插入
		  return    manager.insert("invoice_mergef_line.create", invoiceMergefLine);
		}
		
	}
	
	
	@Override
	public InvoiceMergefLine queryByEntity(@RequestBody InvoiceMergefLine invoiceMergefLine){
	             return (InvoiceMergefLine)manager.query("invoice_mergef_line.query", invoiceMergefLine);
	}
	@Override
	public List<InvoiceMergefLine> queryList(@RequestBody InvoiceMergefLine invoiceMergefLine){
	          return (List<InvoiceMergefLine>)manager.list("invoice_mergef_line.queryList", invoiceMergefLine);
	}
	@Override
    public Pages<List<InvoiceMergefLine>> pages(@RequestBody InvoiceMergefLine invoiceMergefLine, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceMergefLine>>) manager.pages("invoice_mergef_line.page", invoiceMergefLine, page);
	
	}
	/**
    @Override
    public Pages<List<InvoiceMergefLine>> pages(@RequestBody InvoiceMergefLinePojo invoiceMergefLinePojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceMergefLine>>) manager.pages("invoice_mergef_line.page", invoiceMergefLinePojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody InvoiceMergefLine invoiceMergefLine) {
		return manager.update("invoice_mergef_line.changeDelStateById", invoiceMergefLine);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody InvoiceMergefLine invoiceMergefLinePojo) {
		return manager.update("invoice_mergef_line.changeDelStateByIds", invoiceMergefLinePojo);
	}
	
    
}