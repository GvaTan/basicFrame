/**
 * 
 */
package top.anets.dao;

import org.apache.ibatis.annotations.Mapper;

import top.anets.entity.User;

/**
 * @author Administrator
 *
 */
@Mapper
public interface UserDao {

	/**
	* 
	*@param username
	*@return 
	*/
	User findUserByUserName(String username);
   
}
