package com.zhys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhys.base.BaseApiService;
import com.zhys.exception.BusinessException;
import com.zhys.invoice.po.InvoiceHead;
import com.zhys.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhys.service.SQLManager;
import com.zhys.service.TestService;

import java.util.List;

@Slf4j
@RestController
public class ZqfpServiceImpl extends BaseApiService implements TestService {
	
	@Autowired
	private SQLManager manager;

//GnbFarmVO gnbFarmVO=POJOConvertUtil.getConvertMapper().map(farmPO, GnbFarmVO.class);
	@Override
	public   String index() {
		return "1";
	}


	@ResponseResult
	@GetMapping(value = "/index1" ,produces="application/json;charset=UTF-8")
//	@Override
	public   Object index1() {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("result",(List<InvoiceHead>)manager.list("invoice_head.queryList", null));


		return jsonObject;
	}
//	@Override
//	public Zqfp index1() {
//		Zqfp zqfp = new Zqfp();
//		return zqfp;
//	}


}