package top.anets;

import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		
	}
	
	
	public static void main(String[] args) {
		String salt=UUID.randomUUID().toString();
		//String newPassword=
		//DigestUtils.md5DigestAsHex((entity.getPassword()+salt).getBytes());
	    SimpleHash sh=new SimpleHash(
			   "MD5",//algorithmName 算法名称
			   "admin",//source 未加密的密码
			   salt,//盐值
			   1);//hashIterations 加密次数
	    String newPassword=sh.toHex();
	    System.out.println(newPassword);
	}

}
