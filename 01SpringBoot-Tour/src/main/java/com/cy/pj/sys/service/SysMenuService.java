package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

public interface SysMenuService {
	
	/**
	 * 更新菜单业务数据
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	/**
	 * 保存菜单业务数据
	 * @param entity
	 * @return
	 */
	int saveObject(SysMenu entity);
	
	List<Node> findZtreeMenuNodes();
	
	int deleteObject(Integer id);

	List<Map<String,Object>> findObjects();
}
