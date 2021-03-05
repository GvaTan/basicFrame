package com.zhys.controller;

import java.util.Map;

import com.zhys.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.lycheeframework.core.cmp.kit.EasyPage;
import com.lycheeframework.core.cmp.kit.Pages;
import com.zhys.po.CustomerMsg;
import com.zhys.service.CustomerMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Slf4j
@RestController
@RequestMapping("customerMsgs")
@Api(value="接口",description="接口" )
@ResponseResult
public class CustomerMsgController {

    @Autowired
	private CustomerMsgService service;

    private static final String INDEX = "index";

    @ApiOperation(value = "分页列表",notes="根据条件查询数据并分页")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true,paramType="query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageNum", value = "当前第几页", required = true, paramType="query",dataType = "Integer"),
	})
    @PostMapping("/page")
	public @ResponseBody Pages<List<CustomerMsg>> pages(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, @RequestBody CustomerMsg customerMsg){
		return  service.pages(customerMsg,pageSize,pageNum);
	}

    @ApiOperation(value = "保存",notes="保存")
    @PostMapping("/save")
	public @ResponseBody Integer save(@RequestBody @Validated CustomerMsg customerMsg){
		return  service.save(customerMsg);
	}

    @ApiOperation(value = "根据id获取详情",notes="根据id获取详情")
    @GetMapping("/info/{id}")
	public CustomerMsg info(@PathVariable("id") Long id){
	    CustomerMsg customerMsg = new CustomerMsg ();
	    customerMsg.setId(id);
		customerMsg = service.queryByEntity(customerMsg);
		
		return customerMsg;
	}
	
	@ApiOperation(value = "根据查询条件获取列表",notes="根据查询条件获取列表")
    @PostMapping("lists")
	public @ResponseBody List<CustomerMsg> queryList(@RequestBody CustomerMsg customerMsg){
		return  service.queryList(customerMsg);
	}


	@ApiOperation(value = "根据id删除信息",notes="根据id删除信息")
	@GetMapping("/del/{id}")
	public Integer del(@PathVariable("id") Long id){
		CustomerMsg customerMsg = new CustomerMsg ();
		customerMsg.setId(id);
		return service.changeDelStateById(customerMsg);

	}
	
    
}