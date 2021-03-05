package com.zhys.controller;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zhys.invoice.po.CustomizedMaterials;
import com.zhys.result.ResponseResult;
import com.zhys.service.CustomerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.invoice.po.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("customer-api")
@ResponseResult
@CrossOrigin
public class CustomerController {

    @Autowired
	private CustomerService service;



    @PostMapping("/save")
	public @ResponseBody Object save(@RequestBody Customer customer){
		Integer re =   service.save(customer);
		JSONObject jsonObject = new JSONObject();
		if(re!=1){
			//throw new BusinessException("保存失败！");
			jsonObject.put("msg","保存失败");
		}else{
			jsonObject.put("msg","保存成功");
			jsonObject.put("success",true);
		}
		return jsonObject;
	}

	@ApiOperation(value = "客户详情",notes="客户详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "taxNo", value = "税号", required = true,paramType="query", dataType = "String")
	})
    @GetMapping("/info/{taxNo}")
	public Customer info(@PathVariable("taxNo") String taxNo,Customer customer){
		customer.setTaxNo(taxNo);
		customer = service.queryByEntity(customer);
		
		return customer;
	}



	@ApiOperation(value = "客户分页列表",notes="客户分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true,paramType="query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageNum", value = "当前第几页", required = true, paramType="query",dataType = "Integer"),
	})
	@PostMapping(value = "/page",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Pages<List<Customer>> customerPages(@RequestParam Integer pageSize, @RequestParam Integer pageNum, @ApiParam(name="customer",value="查询条件",required=false)
	@RequestBody(required=false) Customer customer){
		return  service.pages(customer,pageSize,pageNum);
	}


	@ApiOperation(value = "客户列表",notes="客户列表")
	@PostMapping("/list")
	public @ResponseBody List<Customer> customerList(@RequestBody(required=false) Customer customer){
		return  service.queryList(customer);
	}

	@ApiOperation(value = "客户删除",notes="客户删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "taxNo", value = "客户税号", required = true,paramType="query", dataType = "String")
	})
	@GetMapping(value = "/del",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object delete(@RequestParam(required=false) String taxNo, Customer customer){
		customer.setTaxNo(taxNo);
		Integer re =   service.del(customer);
		JSONObject jsonObject = new JSONObject();
		if(re!=1){
			jsonObject.put("msg","删除失败");
			jsonObject.put("success",false);
		}else{
			jsonObject.put("msg","删除成功");
			jsonObject.put("success",true);
		}
		return jsonObject;
	}
    
}