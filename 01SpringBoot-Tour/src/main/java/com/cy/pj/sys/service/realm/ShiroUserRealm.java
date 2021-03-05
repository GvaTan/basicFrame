package com.cy.pj.sys.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.util.Assert;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;

@Service //<bean id="shiroUserRealm" class="com.cy.pj.sys.service.realm.ShiroUserRealm">
public class ShiroUserRealm extends AuthorizingRealm {

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysMenuDao sysMenuDao;

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
	/**
	 * 此方法负责完成认证信息的获取以及封装.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//1.获取用户端输入的用户信息
		UsernamePasswordToken upToken=(UsernamePasswordToken) token;
		String username=upToken.getUsername();
		//2.基于用户输入的用户名查询用户信息
		SysUser user=sysUserDao.findUserByUserName(username);
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
	/**
	 * 	负责授权信息的获取和封装,方案1
	 */
	/** 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("===doGetAuthorizationInfo===");
		//1.获取登录用户id
		SysUser user=(SysUser)principals.getPrimaryPrincipal();
		Integer userId=user.getId();
		//2.基于用户id获取用户对应的角色id
		List<Integer> roleIds=
		sysUserRoleDao.findRoleIdsByUserId(userId);
		if(roleIds==null||roleIds.size()==0)
			throw new AuthorizationException();
		//3.基于角色id获取菜单id
		Integer[] array= {};//定义整数数组类型
		List<Integer> menuIds=
		sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		if(menuIds==null||menuIds.size()==0)
			throw new AuthorizationException();
		//4.基于菜单id获取授权标识
		List<String> permisssions=
		sysMenuDao.findPermissions(menuIds.toArray(array));
		if(permisssions==null||permisssions.size()==0)
	    throw new AuthorizationException();
		//5.封装查询结果
		Set<String> setPermissions=new HashSet<>();
		for(String per:permisssions) {
			if(!StringUtils.isEmpty(per)) {
				setPermissions.add(per);
			}
		}
	    SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
	    info.setStringPermissions(setPermissions);
		return info;
	}
	*/
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.获取登录用户id
		SysUser user=(SysUser)principals.getPrimaryPrincipal();
		Integer userId=user.getId();
		//2.基于用户id查询用户权限
		List<String> permisssions=sysUserDao.findUserPermissions(userId);
		if(permisssions==null||permisssions.size()==0)
			throw new AuthorizationException();
		//3.封装查询结果
		Set<String> setPermissions=new HashSet<>();
		for(String per:permisssions) {
			if(!StringUtils.isEmpty(per)) {
				setPermissions.add(per);
			}
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.setStringPermissions(setPermissions);
		return info;
	}

}















