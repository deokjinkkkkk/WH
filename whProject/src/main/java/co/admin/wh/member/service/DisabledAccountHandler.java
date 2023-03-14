package co.admin.wh.member.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class DisabledAccountHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		  
		String path = request.getContextPath();
		if (exception instanceof DisabledException) {
	            response.sendRedirect(path + "/disabled"); // 로그인 차단 페이지로 리다이렉트
	        } else {
	            response.sendRedirect("/login?error"); // 일반 로그인 오류 페이지로 리다이렉트
	        }
		
	}

}
