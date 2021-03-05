package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BaseDao {
	/**
	 * 判定此列对应的值是否已经存在
	 * @param columnName
	 * @param columnValue
	 * @return 统计结果,结果大于0说明是存在的
	 */
	@Select("select count(*) from ${tableName}"
			+ " where ${columnName}=#{columnValue}")
	int isExist(@Param("tableName")String tableName,
			@Param("columnName")String columnName,
			@Param("columnValue")String columnValue);
	
	@Delete("delete from ${tableName} where ${idColumnName}=#{idValue}")
	int deleteById(@Param("tableName")String tableName,
			       @Param("idColumnName")String idColumnName,
			       @Param("idValue")Integer idValue);
			
}
