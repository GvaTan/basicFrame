package com.cy.pj.common.config;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro框架配置类
 * @author Administrator
 */
@Configuration
public class SpringShiroConfig {
	/**
	 * shiro框架的会话管理对象
	 * @return
	 */
	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sManager=
				new DefaultWebSessionManager();
		sManager.setGlobalSessionTimeout(60*60*1000);
		return sManager;
	}
	
	/**
	  * 构建记住我管理器对象
	 * @return
	 */
	@Bean
	public RememberMeManager rememberMeManager() {
		CookieRememberMeManager rm=new CookieRememberMeManager();
		SimpleCookie cookie=new SimpleCookie("rememberMe");
		cookie.setMaxAge(10*60);
		rm.setCookie(cookie);
		return rm;
	}
	
	/**
	   *  配置shiro中缓存管理器对象
	 * @return
	 */
	@Bean
	public CacheManager shiroCacheManager() {
		return new MemoryConstrainedCacheManager();
	}
	/**
	 * Shiro框架的核心安全管理器对象
	 * @Bean 注解描述的方法,其返回值会交给spring管理,spring存储
	 * 此对象时,默认会以方法名为key.
	 */
	@Bean
	public SecurityManager securityManager(Realm realm,
			CacheManager cacheManager,
			RememberMeManager rememberMeManager,
			SessionManager sessionManager) {
		DefaultWebSecurityManager sManager=new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		//将shiro中的cache管理器注入给SecurityManager
		sManager.setCacheManager(cacheManager);
		//设置记住我
		sManager.setRememberMeManager(rememberMeManager);
		//设置会话管理器对象
		sManager.setSessionManager(sessionManager);
		return sManager;
	}
	/**配置过滤器工厂(通过此工厂创建大量过滤器,通过过滤器对请求进行过滤)*/
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(
			SecurityManager securityManager) {
		//创建过滤器工厂bean
		ShiroFilterFactoryBean fBean=new ShiroFilterFactoryBean();
		//设置安全管理器
		fBean.setSecurityManager(securityManager);
		//设置认证url
		fBean.setLoginUrl("/doLoginUI");
		//设置过滤规则
		Map<String,String> fcdMap=new LinkedHashMap<>();
		//静态资源允许匿名访问:"anon"
		fcdMap.put("/bower_components/**","anon");
		fcdMap.put("/build/**","anon");
		fcdMap.put("/dist/**","anon");
		fcdMap.put("/plugins/**","anon");
		fcdMap.put("/user/doLogin", "anon");
		fcdMap.put("/doLogout", "logout");//logout为退出时需要指定的规则
		 //除了匿名访问的资源,其它都要认证("authc")后访问
		//fcdMap.put("/**","authc");
		fcdMap.put("/**","user");//使用记住我功能时需要将authc修改为user
		fBean.setFilterChainDefinitionMap(fcdMap);
		return fBean;
	}
	/**
	   *     配置授权的Advisor,通过此Advisor告诉框架底层,要为指定的对象创建代理对象,
	   *     然后对指定业务方法进行授权检查.
	 * @param securityManager
	 * @return
	 */
	 @Bean
	 public AuthorizationAttributeSourceAdvisor 
	          authorizationAttributeSourceAdvisor(
	 	    		    SecurityManager securityManager) {
	 		        AuthorizationAttributeSourceAdvisor advisor=
	 				new AuthorizationAttributeSourceAdvisor();
	      advisor.setSecurityManager(securityManager);
	 	return advisor;
	 }

	   
}




