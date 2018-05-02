package ethan.etframework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import ethan.etframework.util.StringUtil;

public class LoginFailedHandler implements AuthenticationFailureHandler{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private final String defaultFailureUrl = "/login?error";
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		//如果是ajax请求响应头会有x-requested-with  
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
			String msg = exception.getMessage();
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print("{\"responseCode\":\"Failure\", \"msg\":\""+  msg + "\"}");
            response.getWriter().flush();
        }else{
        	response.sendRedirect(defaultFailureUrl);
        }
		
	}
	
}
