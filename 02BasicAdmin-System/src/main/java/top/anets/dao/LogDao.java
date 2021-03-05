/**
 * 
 */
package top.anets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import top.anets.entity.Log;

@Mapper
public interface LogDao {
    List<Log> selectAll();

	/**
	* 
	*@param log 
	*/
	void insert(Log log);
}
