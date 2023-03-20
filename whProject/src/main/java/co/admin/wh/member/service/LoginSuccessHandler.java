package co.admin.wh.member.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		request.getSession().setAttribute("id", authentication.getPrincipal());
		request.getSession().setAttribute("name", authentication.getName());
		request.getSession().setAttribute("perm", authentication.getAuthorities());
		String path = request.getContextPath();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
			response.sendRedirect(path +"/admemList");
		}else {
			response.sendRedirect(path +"/main");
		}
		
		
	}

}
