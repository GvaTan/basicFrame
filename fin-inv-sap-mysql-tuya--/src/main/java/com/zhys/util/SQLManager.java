/**
 * Copyright 2014-2016 www.lychee.com
 * All rights reserved.
 * 
 * @project
 * @author li.hui
 * @version 2.0
 * @date 2016-08-08
 */
package com.zhys.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lycheeframework.core.cmp.kit.Pages;
import com.lycheeframework.core.model.IPO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * DAO基础方法集：I.U.D.C.Q.L
 * 
 * I is create
 * U is update
 * D is delete
 * C is count
 * Q is query
 * L is list
 * P is pages
 * 
 * @author li.hui
 *
 */
@Component("sqlManager")
public class SQLManager {
	
	private String nameSpace;
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增
	 * @param po
	 * @return
	 */
	public int insert(IPO po) {
		
		return insert("create", po);
	}
	
	/**
	 * 新增
	 * @param sqlId
	 * @param po
	 * @return
	 */
	public int insert(String sqlId, IPO po) {
		
		return this.getSqlSessionTemplate().insert(nameSpace + sqlId, po);
	}

	public int insertObject(String sqlId, Object po) {

		return this.getSqlSessionTemplate().insert(nameSpace + sqlId, po);
	}
	
	/**
	 * 修改
	 * @param po
	 * @return
	 */
	public int update(IPO po) {
		
		return update("update", po);
	}
	
	/**
	 * 修改
	 * @param sqlId
	 * @param po
	 * @return
	 */
	public int update(String sqlId, IPO po) {
		
		return this.getSqlSessionTemplate().update(nameSpace + sqlId, po);
	}

	public int updateObject(String sqlId, Object po) {

		return this.getSqlSessionTemplate().update(nameSpace + sqlId, po);
	}
	
	/**
	 * 删除
	 * @param po
	 * @return
	 */
	public int delete(IPO po) {
		
		return delete("delete", po);
	}
	
	/**
	 * 删除
	 * @param sqlId
	 * @param po
	 * @return
	 */
	public int delete(String sqlId, IPO po) {
		
		return this.getSqlSessionTemplate().delete(nameSpace + sqlId, po);
	}
	
	/**
	 * 根据ID删除
	 * @param sqlId
	 * @param id
	 * @return
	 */
	public int deleteByID(String sqlId, int id) {
		
		return this.getSqlSessionTemplate().delete(nameSpace + sqlId, id);
	}
	
	/**
	 * 查询总数
	 * @param data
	 * @return
	 */
	public int count(Object data) {
		
		return count("count", data);
	}
	
	/**
	 * 查询总数
	 * @param sqlId
	 * @param data
	 * @return
	 */
	public int count(String sqlId, Object data) {
		
		return (Integer) this.getSqlSessionTemplate().selectOne(nameSpace + sqlId, data);
	}
	
	/**
	 * 返回单条对象
	 * @param data
	 * @return
	 */
	public Object query(Object data) {
		
		return query("query", data);
	}
	
	/**
	 * 返回单条对象
	 * @param sqlId
	 * @param data
	 * @return
	 */
	public Object query(String sqlId, Object data) {
		
		return this.getSqlSessionTemplate().selectOne(nameSpace + sqlId, data);
	}
	
	/**
	 * 返回列表(不带分页)
	 * @param sqlId
	 * @param data
	 * @return
	 */
	public List<?> list() {
		
		return list("list");
	}
	
	/**
	 * 返回列表(不带分页)
	 * @param sqlId
	 * @param data
	 * @return
	 */
	public List<?> list(String sqlId) {
		
		return list(sqlId, null);
	}
	
	/**
	 * 返回列表(不带分页)
	 * @param sqlId
	 * @param data
	 * @return
	 */
	public List<?> list(String sqlId, Object data) {
		
		return this.getSqlSessionTemplate().selectList(nameSpace + sqlId, data);
	}
	
	/**
	 * 分页
	 * @param page
	 * @return
	 */
	public Pages<?> pages(Page<?> page) {
		
		return pages(null, page);
	}
	
	/**
	 * 分页
	 * @param data
	 * @param page
	 * @return
	 */
	public Pages<?> pages(Object data, Page<?> page) {
		
		return pages("pages", data, page);
	}
	
	/**
	 * 分页
	 * @param sqlId
	 * @param data
	 * @param page
	 * @return
	 */
	public Pages<?> pages(String sqlId, Object data, Page<?> page) {
		
		return pages(sqlId, data, page, true);
	}
	
	/**
	 * 分页
	 * @param sqlId
	 * @param data
	 * @param page
	 * @param isCount
	 * @return
	 */
	public Pages<List<?>> pages(String sqlId, Object data, Page<?> page, boolean isCount) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), isCount);
		PageHelper.orderBy(page.getOrderBy());
		
		List<?> datas = this.getSqlSessionTemplate().selectList(nameSpace + sqlId, data);
		Pages<List<?>> grid = new Pages<List<?>>();
		try {
			grid.setTotal(((Page<?>)datas).getTotal());
		} catch (Exception e) {
			throw new ClassCastException("PageHelper is not configured：" + e);
		}
		
		grid.setRows(datas);
		
		return grid;
	}
	
	@Autowired
	public void setSqlMapClientForAutowire(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		NameSpace ns = this.getClass().getAnnotation(NameSpace.class);
		if (ns != null) {
			this.nameSpace = ns.value() + ".";
		} else {
			this.nameSpace = "";
		}
		
		return sqlSessionTemplate;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		
		return this.sqlSessionTemplate.getSqlSessionFactory();
	}
	
	public SqlSession getSqlSession() {
		
		return this.getSqlSessionFactory().openSession();
	}
}