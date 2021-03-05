package com.zhys.service;

import org.springframework.web.bind.annotation.RequestMapping;
import com.zhys.base.BaseService;
import com.zhys.po.CustomerMsg;


@RequestMapping("customerMsg")
public interface CustomerMsgService extends BaseService<CustomerMsg,CustomerMsg>{
	
}