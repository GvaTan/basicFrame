package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.util.Assert;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@CacheEvict(value = "menuCache",allEntries = true)
	@Override
	public int updateObject(SysMenu entity) {
		//1.参数校验
		Assert.isNull(entity, "保存对象不能为空");
		Assert.isEmpty(entity.getName(), "菜单名不能为空");
		//2.持久化数据
		int rows=sysMenuDao.updateObject(entity);
		//3.返回 结果
		return rows;
	}
	@CacheEvict(value = "menuCache",allEntries = true)
	@Override
	public int saveObject(SysMenu entity) {
		//1.参数校验
	    Assert.isNull(entity, "保存对象不能为空");
	    Assert.isEmpty(entity.getName(), "菜单名不能为空");
		//2.持久化数据
	    int rows=sysMenuDao.insertObject(entity);
		//3.返回 结果
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	/**
	 * @CacheEvict 描述方法时,表示要清除缓存
	 * 1)value:表示缓存名称
	 * 2)allEntries:表示清除缓存中所有对象
	 */
	@CacheEvict(value = "menuCache",allEntries = true)
	@Override
	public int deleteObject(Integer id) {
		//1.判定参数有效性
		Assert.isValid(id!=null&&id>1, "id值无效");
		//2.查询当前菜单是否有子菜单,并进行校验
		int childCount=sysMenuDao.getChildCount(id);
		if(childCount>0)
			throw new ServiceException("请先删除子菜单");
		//3.删除角色菜单关系数据
		sysRoleMenuDao.deleteById("sys_role_menus","menu_id",id);
		//4.删除菜单自身数据
		int rows=sysMenuDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("记录已经不存在");
		//5.返回结果
		return rows;
	}
	/**
	 * @Cacheable 注解描述方法时,表示要从cache取数据,cache没有调用业务方法查数据
	   *    查到数据放到cache中(Cache的底层实现为一个map对象).
	 * 1)value表示cache的名字
	 */
	@Cacheable(value="menuCache")//Spring框架负责提供Cache对象,ConcurrentHashMap
    @Override
    public List<Map<String, Object>> findObjects() {
		System.out.println("menu.findObjects");
    	return sysMenuDao.findObjects();
    }

	
}









