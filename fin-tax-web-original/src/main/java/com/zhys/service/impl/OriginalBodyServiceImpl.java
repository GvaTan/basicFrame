package com.zhys.service.impl;

import com.lycheeframework.core.cmp.kit.EasyPage;
import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.base.BaseApiService;
import com.zhys.invoice.po.InvoiceOriginalLine;
import com.zhys.service.InvoiceOriginalLineService;
import com.zhys.service.OriginalBodyService;
import com.zhys.service.SQLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class OriginalBodyServiceImpl extends BaseApiService implements OriginalBodyService {
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody InvoiceOriginalLine invoiceOriginalLine){
	  InvoiceOriginalLine c = (InvoiceOriginalLine) manager.query("original_body.query",invoiceOriginalLine);
		if(c != null ){//修改
		  return	manager.update("original_body.update", invoiceOriginalLine);
		}else{//插入
		  return    manager.insert("original_body.create", invoiceOriginalLine);
		}
		
	}
	
	
	@Override
	public InvoiceOriginalLine queryByEntity(@RequestBody InvoiceOriginalLine invoiceOriginalLine){
	             return (InvoiceOriginalLine)manager.query("original_body.query", invoiceOriginalLine);
	}
	@Override
	public List<InvoiceOriginalLine> queryList(@RequestBody InvoiceOriginalLine invoiceOriginalLine){
		List<InvoiceOriginalLine> list =   (List<InvoiceOriginalLine>)manager.list("original_body.queryList", invoiceOriginalLine);
			if(list!=null&&list.size()>0){
				list.forEach(invoiceOriginalLine1 -> {
					int i = list.indexOf(invoiceOriginalLine1);
					invoiceOriginalLine1.setDocLine(i+1001+"");
				});
			}
		return list;
	}
	@Override
    public Pages<List<InvoiceOriginalLine>> pages(@RequestBody InvoiceOriginalLine invoiceOriginalLine, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceOriginalLine>>) manager.pages("original_body.page", invoiceOriginalLine, page);
	
	}
	/**
    @Override
    public Pages<List<InvoiceOriginalLine>> pages(@RequestBody InvoiceOriginalLinePojo invoiceOriginalLinePojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<InvoiceOriginalLine>>) manager.pages("original_body.page", invoiceOriginalLinePojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody InvoiceOriginalLine invoiceOriginalLine) {
		return manager.update("original_body.changeDelStateById", invoiceOriginalLine);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody InvoiceOriginalLine invoiceOriginalLinePojo) {
		return manager.update("original_body.changeDelStateByIds", invoiceOriginalLinePojo);
	}
	
    
}