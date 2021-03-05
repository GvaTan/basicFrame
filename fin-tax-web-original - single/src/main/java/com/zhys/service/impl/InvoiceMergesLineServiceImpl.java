package com.zhys.service.impl;

import com.zhys.invoice.po.InvoiceMergesLine;
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
import com.zhys.service.InvoiceMergesLineService;

@Slf4j
@RestController
public class InvoiceMergesLineServiceImpl extends BaseApiService implements InvoiceMergesLineService{
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody InvoiceMergesLine invoiceMergesLine){
	  InvoiceMergesLine c = (InvoiceMergesLine) manager.query("invoice_merges_line.query",invoiceMergesLine);
		if(c != null ){//修改
		  return	manager.update("invoice_merges_line.update", invoiceMergesLine);
		}else{//插入
		  return    manager.insert("invoice_merges_line.create", invoiceMergesLine);
		}
		
	}
	
	
	@Override
	public InvoiceMergesLine queryByEntity(@RequestBody InvoiceMergesLine invoiceMergesLine){
	             return (InvoiceMergesLine)manager.query("invoice_merges_line.query", invoiceMergesLine);
	}
	@Override
	public List<InvoiceMergesLine> queryList(@RequestBody InvoiceMergesLine invoiceMergesLine){
	          return (List<InvoiceMergesLine>)manager.list("invoice_merges_line.queryList", invoiceMergesLine);
	}
	@Override
    public Pages<List<InvoiceMergesLine>> pages(@RequestBody InvoiceMergesLine invoiceMergesLine, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceMergesLine>>) manager.pages("invoice_merges_line.page", invoiceMergesLine, page);
	
	}
	/**
    @Override
    public Pages<List<InvoiceMergesLine>> pages(@RequestBody InvoiceMergesLinePojo invoiceMergesLinePojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceMergesLine>>) manager.pages("invoice_merges_line.page", invoiceMergesLinePojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody InvoiceMergesLine invoiceMergesLine) {
		return manager.update("invoice_merges_line.changeDelStateById", invoiceMergesLine);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody InvoiceMergesLine invoiceMergesLinePojo) {
		return manager.update("invoice_merges_line.changeDelStateByIds", invoiceMergesLinePojo);
	}
	
    
}