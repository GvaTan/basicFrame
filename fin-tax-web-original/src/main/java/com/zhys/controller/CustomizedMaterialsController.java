package com.zhys.controller;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zhys.invoice.po.CustomizedMaterials;
import com.zhys.result.ResponseResult;
import com.zhys.service.CustomizedMaterialsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("customizedMaterials-api")
@ResponseResult
@CrossOrigin
public class CustomizedMaterialsController {

    @Autowired
	private CustomizedMaterialsService service;


	@PostMapping("/save")
	public @ResponseBody Object save(@RequestBody CustomizedMaterials CustomizedMaterials){
		Integer re =   service.save(CustomizedMaterials);
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

	@ApiOperation(value = "客户化物料详情",notes="客户化物料详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "taxNo", value = "税号", required = true,paramType="query", dataType = "String")
	})
	@GetMapping("/info/{taxNo}")
	public CustomizedMaterials info(@PathVariable("taxNo") String taxNo,CustomizedMaterials customizedMaterials){
		customizedMaterials.setTaxNo(taxNo);
		customizedMaterials = service.queryByEntity(customizedMaterials);

		return customizedMaterials;
	}



	@ApiOperation(value = "客户化物料分页列表",notes="客户化物料分页列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页多少条数据", required = true,paramType="query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageNum", value = "当前第几页", required = true, paramType="query",dataType = "Integer"),
	})
	@PostMapping(value = "/page",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Pages<List<CustomizedMaterials>> CustomizedMaterialsPages(@RequestParam Integer pageSize, @RequestParam Integer pageNum, @ApiParam(name="customizedMaterials",value="查询条件",required=false)
	@RequestBody(required=false)CustomizedMaterials customizedMaterials){
		return  service.pages(customizedMaterials,pageSize,pageNum);
	}


	@ApiOperation(value = "客户化物料列表",notes="客户化物料列表")
	@PostMapping("/list")
	public @ResponseBody List<CustomizedMaterials> CustomizedMaterialsList(@RequestBody CustomizedMaterials customizedMaterials){
		return  service.queryList(customizedMaterials);
	}

	@ApiOperation(value = "客户化物料删除",notes="客户化物料删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemNumOriginal", value = "原始物料编码(多条用逗号分隔)", required = true, paramType="query",dataType = "String"),
			@ApiImplicitParam(name = "taxNo", value = "客户税号", required = true,paramType="query", dataType = "String")
	})
	@GetMapping(value = "/del",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object delete(@RequestParam String itemNumOriginal,
	@RequestParam(required=false) String taxNo, CustomizedMaterials customizedMaterials){
		customizedMaterials.setTaxNo(taxNo);
		customizedMaterials.setItemNumOriginal(itemNumOriginal);
		Integer re =   service.del(customizedMaterials);
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