package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao extends BaseDao{
	
	/**
	 * 基于用户id查询用户权限信息
	 * @param userId
	 * @return
	 */
	List<String> findUserPermissions(Integer userId);
	
	/**
	 * 修改登录用户密码
	 * @param password
	 * @param salt
	 * @param id
	 * @return
	 * 
	 */
	
	int updatePassword(@Param("password")String password,
			           @Param("salt")String salt,
			           @Param("id")Integer id);
	/**
	 * 基于用户名查找用户信息
	 * @param username
	 * @return
	 */
	@Select("select * from sys_users where username=#{username}")
	SysUser findUserByUserName(String username);
	
	/**
	 * 基于id查询用户以及用户对应的部门信息
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	
	/**
	 * 	持久化用户自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
	/**
	 * 	持久化用户自身信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
	/**
	 * 	用户禁用,启用操作实现
	 * @param id 用户id
	 * @param valid 状态
	 * @param modifiedUser 修改用户
	 * @return 行数
	 */
	@Update("update sys_users set valid=#{valid},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);

	   /**
	    * 	基于条件查询当前页记录
	    * @param username
	    * @param startIndex
	    * @param pageSize
	    * @return
	    */
	   List<SysUserDeptVo> findPageObjects(
			   @Param("username") String username);
	   
	
}
