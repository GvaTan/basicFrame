package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	
	   /**
	    * 	查询所有角色的id,name
	    * @return
	    */
	   @Select("select id,name from sys_roles")
	   List<CheckBox> findObjects();
	
	   /**
	    *	 基于角色id查询角色以及角色对应的菜单信息
	    * @param id
	    * @return
	    */
	   SysRoleMenuVo findObjectById(Integer id);
	
	   /**
	    * 	更新角色自身信息
	    * @param entity
	    * @return
	    */
	   int updateObject(SysRole entity);
	   /**
	    * 	写入角色自身信息
	    * @param entity
	    * @return
	    */
	   int insertObject(SysRole entity);
	
	   /**
	    * 	基于角色id删除角色和用户关系数据
	    * @param id
	    * @return
	    */
	   @Delete("delete from sys_roles where id=#{id}")
	   int deleteObject(Integer id);
	   /**
	    * 	基于条件查询当前页要呈现的记录
	    * @param name
	    * @param startIndex
	    * @param pageSize
	    * @return 当前页要呈现的记录
	    */
	   List<SysRole> findPageObjects(@Param("name")String name);
	   
}



