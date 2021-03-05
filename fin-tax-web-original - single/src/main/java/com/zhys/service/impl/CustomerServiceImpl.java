package com.zhys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhys.exception.BusinessException;
import com.zhys.invoice.po.Customer;
import com.zhys.result.ResultCode;
import com.zhys.service.SQLManager;
import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.cmp.kit.EasyPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhys.base.BaseApiService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import com.zhys.service.CustomerService;

@Slf4j
@RestController
public class CustomerServiceImpl extends BaseApiService implements CustomerService{
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody Customer customer){
	  Customer c = (Customer) manager.query("customer.query",customer);
		if(c != null&& !StringUtils.isEmpty(c.getTaxNo())){//修改
		  return	manager.update("customer.update", customer);
		}else{//插入
		  return    manager.insert("customer.create", customer);
		}
		
	}
	
	
	@Override
	public Customer queryByEntity(@RequestBody Customer customer){
	             return (Customer)manager.query("customer.query", customer);
	}
	@Override
	public List<Customer> queryList(@RequestBody Customer customer){
	          return (List<Customer>)manager.list("customer.queryList", customer);
	}
	@Override
    public Pages<List<Customer>> pages(@RequestBody Customer customer, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<Customer>>) manager.pages("customer.page", customer, page);
	
	}
	/**
    @Override
    public Pages<List<Customer>> pages(@RequestBody CustomerPojo customerPojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<Customer>>) manager.pages("customer.page", customerPojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody Customer customer) {
		return manager.update("customer.changeDelStateById", customer);
	}

	@Override
	public Integer changeDelStateByIds(Customer t) {
		return null;
	}


	@Override
	public Integer del(Customer t) {
		if(StringUtils.isEmpty(t.getTaxNo())){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","参数值不符");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.PARAM_IS_INVALID,jsonObject);
		}
		return manager.delete("customer.del", t);
	}
}