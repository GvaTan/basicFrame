package top.anets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import top.anets.pojo.User;

@Mapper
public interface UserMapper {
	@Select("select * from user")
     List<User> findAll();
	
	
	List<User> findUsers();
	
	
	
}
