package com.zhys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhys.exception.BusinessException;
import com.zhys.invoice.po.CustomizedMaterials;
import com.zhys.result.ResultCode;
import com.zhys.service.SQLManager;
import java.util.List;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.cmp.kit.EasyPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhys.base.BaseApiService;
import com.zhys.base.ResponseBase;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import com.zhys.service.CustomizedMaterialsService;

@Slf4j
@RestController
public class CustomizedMaterialsServiceImpl extends BaseApiService implements CustomizedMaterialsService{
	
	@Autowired
	private SQLManager manager;
	
	
	@Override
	public Integer save(@RequestBody CustomizedMaterials customizedMaterials){
		if(StringUtils.isEmpty(customizedMaterials.getTaxNo())||StringUtils.isEmpty(customizedMaterials.getItemNumOriginal())){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","参数值不符");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.PARAM_IS_INVALID,jsonObject);
		}
	  CustomizedMaterials c = (CustomizedMaterials) manager.query("customized_materials.query",customizedMaterials);
		if(c != null&&!StringUtils.isEmpty(c.getTaxNo()) ){//修改
		  return	manager.update("customized_materials.update", customizedMaterials);
		}else{//插入
		  return    manager.insert("customized_materials.create", customizedMaterials);
		}
		
	}
	
	
	@Override
	public CustomizedMaterials queryByEntity(@RequestBody CustomizedMaterials customizedMaterials){
		if(StringUtils.isEmpty(customizedMaterials.getTaxNo())||StringUtils.isEmpty(customizedMaterials.getItemNumOriginal())){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","参数值不符");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.PARAM_IS_INVALID,jsonObject);
		}
	             return (CustomizedMaterials)manager.query("customized_materials.query", customizedMaterials);
	}
	@Override
	public List<CustomizedMaterials> queryList(@RequestBody CustomizedMaterials customizedMaterials){
	          return (List<CustomizedMaterials>)manager.list("customized_materials.queryList", customizedMaterials);
	}
	@Override
    public Pages<List<CustomizedMaterials>> pages(@RequestBody CustomizedMaterials customizedMaterials, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<CustomizedMaterials>>) manager.pages("customized_materials.page", customizedMaterials, page);
	
	}
	/**
    @Override
    public Pages<List<CustomizedMaterials>> pages(@RequestBody CustomizedMaterialsPojo customizedMaterialsPojo, Integer pageSize, Integer pageNum){
    EasyPage page = new EasyPage();
	    page.pageNum(pageNum);
	    page.pageSize(pageSize);
	    return (Pages<List<CustomizedMaterials>>) manager.pages("customized_materials.page", customizedMaterialsPojo, page);
	
	}**/
	
	@Override
	public Integer changeDelStateById(@RequestBody CustomizedMaterials customizedMaterials) {
		return manager.update("customized_materials.changeDelStateById", customizedMaterials);
	}


	@Override
	public Integer changeDelStateByIds(@RequestBody CustomizedMaterials customizedMaterialsPojo) {
		return manager.update("customized_materials.changeDelStateByIds", customizedMaterialsPojo);
	}


	@Override
	public Integer del(CustomizedMaterials t) {
		if(StringUtils.isEmpty(t.getTaxNo())||StringUtils.isEmpty(t.getItemNumOriginal())){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg","参数值不符");
			jsonObject.put("success",false);
			throw  new BusinessException(ResultCode.PARAM_IS_INVALID,jsonObject);
		}
		return manager.delete("customized_materials.del", t);
	}
}