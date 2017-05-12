package com.zc.shiro.web.filter.authc;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.zc.api.DataResponse;
import com.zc.api.Global;
import com.zc.api.JsonUtil;
import com.zc.api.loginData;
import com.zc.mapper.CommonImpl;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthcAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthcAuthenticationFilter.class);
	private CommonImpl commonImpl = new CommonImpl();

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				// allow them to see the login page ;)
				return true;
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			log.info("身份认证不通过");
			return false;
		}
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		JSONObject jsonObject = getRequsetJsonObject(httpServletRequest);
		String account = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		String rememberMeParam = jsonObject.getString("rememberMe");
		boolean rememberMe = isRememberMe(rememberMeParam);
        String host = getHost(request);
        return super.createToken(account, password, rememberMe, host);
	}
	
	protected boolean isRememberMe(String rememberMe) {
		// TODO Auto-generated method stub
		 return rememberMe != null &&
	                (rememberMe.equalsIgnoreCase("true") ||
	                		rememberMe.equalsIgnoreCase("t") ||
	                		rememberMe.equalsIgnoreCase("1") ||
	                		rememberMe.equalsIgnoreCase("enabled") ||
	                		rememberMe.equalsIgnoreCase("y") ||
	                        rememberMe.equalsIgnoreCase("yes") ||
	                        rememberMe.equalsIgnoreCase("on"));
	}

	public static JSONObject getRequsetJsonObject(HttpServletRequest request) {
		try {
			JSONObject jsonObject = new JSONObject(getRequestJsonString(request));
			return jsonObject;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getRequestJsonString(HttpServletRequest request) throws IOException {
		String submitMehtod = request.getMethod();
		// GET
		if (submitMehtod.equals("GET")) {
			return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
			// POST
		} else {
			return getRequestPostStr(request);
		}
	}

	public static String getRequestPostStr(HttpServletRequest request) throws IOException {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}

	public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		log.info("身份认证通过");
		DataResponse dataResponse = new DataResponse();
		dataResponse = commonImpl.responseDeal(dataResponse, Global.SUCCESS, "/showUser.html", "登陆成功");
		JsonUtil.responseOutWithJson(response, dataResponse);
		return false;
	}

}
