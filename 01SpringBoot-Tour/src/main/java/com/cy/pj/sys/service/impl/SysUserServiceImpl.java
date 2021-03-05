package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.util.Assert;
import com.cy.pj.common.util.ShiroUtil;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;
import com.cy.pj.sys.vo.SysUserMenuVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;
@Transactional(timeout = 30,
               rollbackFor = Throwable.class,
               isolation = Isolation.READ_COMMITTED,
               readOnly = false)
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;//依赖,DIP原则
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private PageProperties pageProperties;
	/**
	 * 基于用户id查询用户对应的一级菜单,二级菜单
	 */
	@Override
	public List<SysUserMenuVo> findUserMenusByUserId(Integer id) {
		//1.对用户id进行判断
		Assert.isValid(id!=null&&id>0, "id值无效");
		//2.基于用户id查找用户对应的角色id
		List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(id);
		//3.基于角色id获取角色对应的菜单信息,并进行封装.
		List<Integer> menuIds=
		sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(new Integer[] {}));
		//4.基于菜单id获取用户对应的菜单信息并返回
		return sysMenuDao.findMenusByIds(menuIds);
	}
	
	@Override
	public int updatePassword(String password, 
			String newPasssword, 
			String cfgPassword) {
		//1.参数校验
		Assert.isEmpty(password, "原密码不能为空");
		Assert.isEmpty(newPasssword, "新密码不能为空");
		Assert.isValid(newPasssword.equals(cfgPassword), "两次密码输入不一致");
		//检查原密码是否正确
		SysUser user=ShiroUtil.getLoginUser();
		String salt=user.getSalt();
		SimpleHash sh=new SimpleHash("MD5",password, salt,1);
		String hashedPwd=sh.toHex();
		Assert.isValid(user.getPassword().equals(hashedPwd),"原密码不正确");
		//2.更新密码
		String newSalt=UUID.randomUUID().toString();
		String newHashedPwd=new SimpleHash("MD5",cfgPassword, newSalt,1).toHex();
		int rows=sysUserDao.updatePassword(newHashedPwd, newSalt,user.getId());
		if(rows==0)
			throw new ServiceException("更新失败");
		//3.返回结果
		return rows;
	}
	
	@Override
	public int isExists(String columnName, String columnValue) {
		//1.参数校验
		Assert.isEmpty(columnValue, "字段值不正确");
		//2.基于字段以及值进行统计查询
		return sysUserDao.isExist("sys_users",columnName, columnValue);
	}
	
	@Override
	public Map<String, Object> findObjectById(Integer id) {
	    //1.参数校验
		Assert.isValid(id!=null&&id>0, "id值无效");
		//2.查询用户以及用户对应的部门信息
		SysUserDeptVo user=sysUserDao.findObjectById(id);
		if(user==null)
			throw new ServiceException("用户不存在");
		//3.查询用户对应的角色信息
		List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(id);
		//4.封装结果并返回
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}
	
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		//1.参数校验
		Assert.isNull(entity, "保存对象不能为空");
		Assert.isEmpty(entity.getUsername(), "用户名不能为空");
		Assert.isEmpty(roleIds, "必须为用户分配角色");
		//2.保存用户自身信息
		int rows=sysUserDao.updateObject(entity);
		//3.保存用户和角色关系数据
		sysUserRoleDao.deleteById("sys_user_roles","user_id",entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		//4.返回结果
		return rows;
	}
	
	@RequiredLog(operation = "添加用户")
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		//1.参数校验
		Assert.isNull(entity, "保存对象不能为空");
		Assert.isEmpty(entity.getUsername(), "用户名不能为空");
		Assert.isEmpty(entity.getPassword(), "密码不能为空");
		Assert.isEmpty(roleIds, "必须为用户分配角色");
		//2.保存用户自身信息
		//2.1对密码进行加密
		String salt=UUID.randomUUID().toString();
		//String newPassword=
		//DigestUtils.md5DigestAsHex((entity.getPassword()+salt).getBytes());
	    SimpleHash sh=new SimpleHash(
			   "MD5",//algorithmName 算法名称
			   entity.getPassword(),//source 未加密的密码
			   salt,//盐值
			   1);//hashIterations 加密次数
	    String newPassword=sh.toHex();
		//2.2重新存储到entity对象
	    entity.setSalt(salt);
	    entity.setPassword(newPassword);
		//2.3持久化用户信息
	    int rows=sysUserDao.insertObject(entity);
		//3.保存用户和角色关系数据
	    sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		//4.返回结果
		return rows;
	}

	/**使用@RequiresPermissions 注解描述业务方法时,
	 * 表示此方法在访问时,需要进行授权(条件是登录用户必须
	 * 具备访问这个方法的权限).
	 */
	@RequiresPermissions("sys:user:update")
	@RequiredLog(operation = "禁用启用")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		long t1=System.currentTimeMillis();
		//1.参数校验
		Assert.isValid(id!=null&&id>0, "id值无效");
		Assert.isValid(valid!=null&&(valid==1||valid==0), "状态无效");
		//2.修改状态
		int rows=sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		long t2=System.currentTimeMillis();
	    log.info("SysUserServiceImpl.validById execute time {}",(t2-t1));
		//3.返回结果
		return rows;
	}
	@RequiredLog(operation = "查询用户")
	@Transactional(readOnly = true)
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		String tName=Thread.currentThread().getName();
		System.out.println("User.Thread.name="+tName);
		//1.参数校验
		Assert.isValid(pageCurrent!=null&&pageCurrent>0, "当前页码值不正确");
		//2.设置起始位置
		int pageSize=pageProperties.getPageSize();
		Page<SysUserDeptVo> page=PageHelper.startPage(pageCurrent, pageSize);
		//3.查询当前页记录
		List<SysUserDeptVo> records=
		sysUserDao.findPageObjects(username);
		//4.封装查询结果
		return new PageObject<>(records,(int)page.getTotal(), pageCurrent, pageSize);
	}
}
