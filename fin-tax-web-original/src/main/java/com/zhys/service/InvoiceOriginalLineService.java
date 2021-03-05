package com.zhys.service;

import org.springframework.web.bind.annotation.RequestMapping;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceOriginalLine;

@RequestMapping("invoiceOriginalLine")
public interface InvoiceOriginalLineService extends BaseService<InvoiceOriginalLine,InvoiceOriginalLine>{
	
}