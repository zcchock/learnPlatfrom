package com.zc.shiro.web.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogoutFilter extends LogoutFilter {
	private static final Logger log = LoggerFactory.getLogger(CustomLogoutFilter.class);
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		Subject subject = getSubject(request, response);
        //try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
		log.info("登陆退出成功");
        return false;
	}
}
