package co.admin.wh.member.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class DisabledAccountHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		  
		String path = request.getContextPath();
		if(exception instanceof AuthenticationServiceException) {
			response.sendRedirect("/login?error");
		}
		else if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			 response.sendRedirect("/login?error"); //비밀번호 체크
		}
		else if (exception instanceof DisabledException) {
	         response.sendRedirect(path + "/disabled"); // 로그인 차단 페이지로 리다이렉트
	    }else if(exception instanceof SessionAuthenticationException) {
	    	response.sendRedirect("/login?error");  //중복로그인
		}
		
	}

}
