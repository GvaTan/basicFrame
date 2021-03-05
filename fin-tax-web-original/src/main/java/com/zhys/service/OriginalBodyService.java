package com.zhys.service;

import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceOriginalLine;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("originalBody")
public interface OriginalBodyService extends BaseService<InvoiceOriginalLine,InvoiceOriginalLine>{
	
}