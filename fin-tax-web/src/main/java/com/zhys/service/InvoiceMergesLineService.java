package com.zhys.service;

import org.springframework.web.bind.annotation.RequestMapping;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceMergesLine;

@RequestMapping("invoiceMergesLine")
public interface InvoiceMergesLineService extends BaseService<InvoiceMergesLine,InvoiceMergesLine>{
	
}