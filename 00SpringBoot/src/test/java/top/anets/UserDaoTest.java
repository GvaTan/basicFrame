package top.anets;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.anets.dao.UserMapper;
import top.anets.pojo.User;


public class UserDaoTest {
   @Autowired
   private UserMapper userMapper;
   
   @Test
   public void fun() throws UnknownHostException {
//	   List<User> findAll = userMapper.findUsers();
//	   for (User user : findAll) {
//		 System.out.println(user.getUsername());
//	   }
   }
}
