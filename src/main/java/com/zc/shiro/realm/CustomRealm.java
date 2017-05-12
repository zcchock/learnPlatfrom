package com.zc.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import com.zc.entity.User;
import com.zc.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zc.shiro.model.UserCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String account = (String) principals.getPrimaryPrincipal();
		logger.info("角色授权:"+account);
		//查询账号角色
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roleSet = new HashSet<String>();
		roleSet.add("admin");
		roleSet.add("normal");
		authorizationInfo.setRoles(roleSet);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String account = (String) token.getPrincipal();
		logger.info("身份认证:"+account);
		//查询账户
		UserCertificate userCertificate = new UserCertificate();

		User user = userMapper.queryUserByName(account);

		userCertificate.setAccount(account);
		userCertificate.setPassword(user.getPassword());
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				userCertificate.getAccount(), 
				userCertificate.getPassword(),
			   this.getName());
		
		return simpleAuthenticationInfo;
		
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
