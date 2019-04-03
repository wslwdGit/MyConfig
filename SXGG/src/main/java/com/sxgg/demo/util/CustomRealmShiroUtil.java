package com.sxgg.demo.util;

import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxgg.demo.pojo.Myrole;
import com.sxgg.demo.pojo.Myuser;
import com.sxgg.demo.service.TestServiceImp;

public class CustomRealmShiroUtil extends AuthorizingRealm {
	
	@Autowired
	private TestServiceImp testservice;;
	
	/**
             * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //取出用户验证好的用户信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Myuser object=(Myuser)principalCollection.getPrimaryPrincipal();
        //获得该用户角色 当前单一角色
        String role = object.getRoleMyrole().getRname();
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        //添加权限
        //info.addStringPermission("operation");
        System.err.println("授权成功,当前角色:"+role);
        return info;
    }
 

	
	
	
	 /**
            * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //取出输入的用户名和密码
		String userName = token.getPrincipal().toString();
		String pwd=new String((char[]) token.getCredentials());
		//查询用户信息
        //Myuser myuser=testservice.getMyusernameService(userName);
		//进行验证
        if(!userName.equals("root")) {
        	 throw new AccountException("用户名不正确");
        }else if(!"123".equals(new String((char[]) token.getCredentials()))) {
        	 throw new AccountException("密码不正确");
        }
        Myuser myuser=new Myuser();
        Myrole myrole=new Myrole();
        myrole.setRname("admin");
        myuser.setUserName("root");
        myuser.setPassword("123");
        myuser.setRoleMyrole(myrole);
        System.err.println("身份验证成功");
        //验证成功把用户信息存入SimpleAuthorizationInfo中 
        //在doGetAuthorizationInfo中可以取出进行授权操作
        return new SimpleAuthenticationInfo(myuser, pwd, getName());
	}

}
