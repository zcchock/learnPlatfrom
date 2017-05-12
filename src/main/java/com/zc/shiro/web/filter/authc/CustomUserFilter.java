package com.zc.shiro.web.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zc.entity.User;
import com.zc.mapper.UserMapper;
import com.zc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserFilter extends PathMatchingFilter {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String account = (String) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute("userAcc", account);
		logger.info("CURRENT_USER:"+ account);
		return true;
	}
	
}
