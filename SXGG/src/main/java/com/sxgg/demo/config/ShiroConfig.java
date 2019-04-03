
package com.sxgg.demo.config;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sxgg.demo.util.CustomRealmShiroUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;

@Configuration
public class ShiroConfig {

	@Bean 
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) { 
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
  
		  // 必须设置 SecurityManager
		  shiroFilterFactoryBean.setSecurityManager(securityManager);
		  
		  // 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
		  shiroFilterFactoryBean.setLoginUrl("/html/login.html");

		  
		  // 拦截器. 
		  Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>(); 
		  // 配置不会被拦截的链接 顺序判断
		  
		  //游客，开发权限 
		  filterChainDefinitionMap.put("/html/login.html", "anon");   //登陆页面
		  filterChainDefinitionMap.put("/login", "anon");       //登陆接口

		  //静态资源 
		  filterChainDefinitionMap.put("/js/**", "anon");
		  filterChainDefinitionMap.put("/css/**", "anon");
		 
		  //退出 
		  //filterChainDefinitionMap.put("/user/logout", "logout");
		  
		  // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 :这是一个坑呢，一不小心代码就不好使了; 
		  // ①authc:所有url都必须认证通过才可以访问; ② anon:所有url都都可以匿名访问
		  filterChainDefinitionMap.put("/**", "authc");
		  shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		  System.err.println("Shiro拦截器工厂类注入成功"); return shiroFilterFactoryBean; }
		
			/**
			 * 注入 securityManager
			 */
		
			@Bean
			public SecurityManager securityManager() {
				DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(); // 设置realm.
				securityManager.setRealm(customRealmShiroUtil());
				return securityManager;
			}
		
			/**
			 * 自定义身份认证 realm;
			 * <p>
			 * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm， 否则会影响 CustomRealm类 中其他类的依赖注入
			 */
			@Bean
			public CustomRealmShiroUtil customRealmShiroUtil() {
				return new CustomRealmShiroUtil();
			}
			
			//加入注解的使用，不加入这个注解不生效
		    @Bean
		    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		        advisor.setSecurityManager(securityManager);
		        return advisor;
		    }
		    
		    //权限管理，配置主要是Realm的管理认证
		    @Bean
		    public org.apache.shiro.mgt.SecurityManager securityManager(CacheManager cacheManager, SessionManager sessionManager) {
		        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		        securityManager.setSessionManager(sessionManager);
		        securityManager.setRealm(customRealmShiroUtil());
		        securityManager.setCacheManager(cacheManager);
		        return securityManager;
		    }
}
