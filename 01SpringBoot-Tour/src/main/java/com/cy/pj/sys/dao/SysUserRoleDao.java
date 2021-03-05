package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao extends BaseDao{
	
	/**
	 * 基于用户id查询用户对应的角色id
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);
	
	/**
	 * 写入用户和角色的关系数据
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insertObjects(@Param("userId")Integer userId,
			          @Param("roleIds")Integer[] roleIds);
	
	
	
	
	
	
	
}







