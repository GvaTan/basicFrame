package top.anets.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import top.anets.dao.UserDao;
import top.anets.entity.User;

/**
 * @author Administrator
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private UserDao userDao;
	
	
	/**
	 * 设置加密算法
	 */
	@Override
	public CredentialsMatcher getCredentialsMatcher() {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=
				new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		return cMatcher;

	}
	

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户端输入的用户信息
				UsernamePasswordToken upToken=(UsernamePasswordToken) token;
				String username=upToken.getUsername();
				//2.基于用户输入的用户名查询用户信息
				User user=userDao.findUserByUserName(username);
				//3.对用户信息进行基本校验
				if(user==null)
					throw new UnknownAccountException();
				if(user.getValid()==0)
					throw new LockedAccountException();
				//4.封装用户信息并返回
				ByteSource credentialsSalt=
				ByteSource.Util.bytes(user.getSalt().getBytes());
				SimpleAuthenticationInfo info=
				new SimpleAuthenticationInfo(
						user, //principal 身份
						user.getPassword(),//hashedCredentials 已加密的密码
						credentialsSalt,//credentialsSalt
						this.getName());//realmName
				return info;//交给认证管理器(SecurityManager)
	}

}
