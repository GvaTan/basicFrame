package com.zhys.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.Customer;

@RequestMapping("customer")
public interface CustomerService extends BaseService<Customer,Customer>{
    @PostMapping("/del")
    Integer del(@RequestBody Customer t);
}