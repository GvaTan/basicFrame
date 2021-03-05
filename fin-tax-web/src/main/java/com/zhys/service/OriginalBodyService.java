package com.zhys.service;

import com.zhys.base.BaseService;
import com.zhys.invoice.po.InvoiceOriginalLine;
import com.zhys.po.OriginalBodyDto;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("originalBody")
public interface OriginalBodyService extends BaseService<OriginalBodyDto,OriginalBodyDto>{

}