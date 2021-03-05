package com.zhys.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhys.base.BaseService;
import com.zhys.invoice.po.CustomizedMaterials;

@RequestMapping("customizedMaterials")
public interface CustomizedMaterialsService extends BaseService<CustomizedMaterials,CustomizedMaterials>{
    @PostMapping("/del")
    Integer del(@RequestBody CustomizedMaterials t);
}