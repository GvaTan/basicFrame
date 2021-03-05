/**
 * 
 */
package top.anets.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 *
 */
@Configuration
public class SpringShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    	ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    	shiroFilterFactoryBean.setSecurityManager(securityManager);
    	
    	shiroFilterFactoryBean.setLoginUrl("/system/login");
    	
    	Map<String, String> map=new HashMap<>();
    	map.put("/bower_components/**","anon");
		map.put("/build/**","anon");
		map.put("/dist/**","anon");
		map.put("/plugins/**","anon");
		map.put("/admin/login", "anon");
		map.put("/admin/logout", "logout");//logout为退出时需要指定的规则
		
		map.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
    	return shiroFilterFactoryBean;
    }
    
    
    @Bean
    public SecurityManager securityManager(Realm realm) {
    	DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
    	securityManager.setRealm(realm);
       return securityManager;
    }
}
