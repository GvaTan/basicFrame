package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.util.Assert;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private PageProperties pageProperties;
	
	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}
	
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.参数校验
		Assert.isValid(id!=null&&id>0, "id值无效");
		//2.基于id查询角色以及对应菜单信息
		SysRoleMenuVo rm=sysRoleDao.findObjectById(id);
		if(rm==null)
			throw new ServiceException("记录不存在");
		//3.返回查询结果
		return rm;
	}
	
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.参数校验
		Assert.isNull(entity, "保存对象不能为空");
		Assert.isEmpty(entity.getName(), "角色名不能为空");
		Assert.isEmpty(menuIds, "必须为角色授权");
		//2.保存角色自身信息
		int rows=sysRoleDao.updateObject(entity);
		//3.保存角色菜单关系数据
		sysRoleMenuDao.deleteById("sys_role_menus","role_id",entity.getId());
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		return rows;
	}
	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		//1.参数校验
		Assert.isNull(entity, "保存对象不能为空");
		Assert.isEmpty(entity.getName(), "角色名不能为空");
		Assert.isEmpty(menuIds, "必须为角色授权");
		//2.保存角色自身信息
		int rows=sysRoleDao.insertObject(entity);
		//3.保存角色菜单关系数据
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		return rows;
	}
	
	@Override
	public int deleteObject(Integer id) {
		//1.参数校验
		 Assert.isValid(id!=null&&id>0, "id值无效");
		//2.删除关系数据
		 sysRoleMenuDao.deleteById("sys_role_menus","role_id",id);
		 sysUserRoleDao.deleteById("sys_user_roles","role_id",id);
		//3.删除自身信息
		 int rows=sysRoleDao.deleteObject(id);
		//4.校验并返回结果
		 if(rows==0)
			 throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		//1.验证参数有效性
		Assert.isValid(pageCurrent!=null&&pageCurrent>0, "页码值无效");
		//2.设置查询起始位置
		int pageSize=pageProperties.getPageSize();
        Page<SysRole> page=PageHelper.startPage(pageCurrent,pageSize);
		//3.查询当前页要呈现的记录
		List<SysRole> records=sysRoleDao.findPageObjects(name);
		//4.对查询结果进行封装
		return new PageObject<>(records,(int)page.getTotal(), pageCurrent, pageSize);
	}

}
