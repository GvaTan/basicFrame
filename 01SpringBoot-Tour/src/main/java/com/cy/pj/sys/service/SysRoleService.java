package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	
	List<CheckBox> findObjects();
	
	SysRoleMenuVo findObjectById(Integer id);
	
	/**
	 * 更新角色以及角色对应的菜单关系数据
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int updateObject(SysRole entity,Integer[]menuIds);
	
	
	/**
	  * 保存角色以及角色对应的菜单关系数据
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int saveObject(SysRole entity,Integer[]menuIds);
	
	/**
	 * 	基于id删除角色以及对应的关系数据
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);

	/**
	 * 	分页查询角色信息
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
}
