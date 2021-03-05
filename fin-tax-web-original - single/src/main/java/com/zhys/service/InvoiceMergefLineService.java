package com.zhys.service;

import org.springframework.web.bind.annotation.RequestMapping;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceMergefLine;

@RequestMapping("invoiceMergefLine")
public interface InvoiceMergefLineService extends BaseService<InvoiceMergefLine,InvoiceMergefLine>{
	
}