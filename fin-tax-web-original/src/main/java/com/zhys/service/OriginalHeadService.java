package com.zhys.service;

import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceHead;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("originalHead")
public interface OriginalHeadService extends BaseService<InvoiceHead,InvoiceHead>{

    Integer delByIds(@RequestBody InvoiceHead invoiceHead);


}