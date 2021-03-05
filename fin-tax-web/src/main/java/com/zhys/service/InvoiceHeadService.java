package com.zhys.service;

import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.po.InvoiceHeadDto;
import org.springframework.web.bind.annotation.*;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceHead;

import java.util.List;

@RequestMapping("invoiceHead")
public interface InvoiceHeadService extends BaseService<InvoiceHead,InvoiceHead>{

    boolean del(InvoiceHead invoiceHead);

    //type 指来源 0 ：保存 1：提交
    Integer save(@RequestBody InvoiceHead invoiceHead,String type);

    Object invoice(InvoiceHeadDto paramInvoiceHeadDto);
}