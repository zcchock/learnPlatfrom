package com.zc.shiro.web.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAuthenticationFilter extends UserFilter {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.info("身份未通过认证");
        return false;
	}
	
}
