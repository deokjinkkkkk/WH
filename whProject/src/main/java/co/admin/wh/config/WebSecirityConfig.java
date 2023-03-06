package co.admin.wh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.admin.wh.member.service.LoginSuccessHandler;
import co.admin.wh.member.service.UsersService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecirityConfig{
	private final UsersService usersSerivce;
	
	
	
	@Bean
	public UserDetailsService UserDetailsService() {
		return usersSerivce;
	}
//	@Bean
//		public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/login").permitAll()
				//.anyRequest().permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/memberLogin")
				.passwordParameter("pass")
				.usernameParameter("id")
				.successHandler(new LoginSuccessHandler())
				.permitAll()
			)
			.logout((logout) -> logout
				.permitAll()
				.logoutUrl("/logout")
			)
			.csrf().disable();
		

		return http.build();
	}
	  @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers("/css", "/fonts");
	    }

	  @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        // 사용자 인증을 위한 서비스를 설정합니다.
	        auth.userDetailsService(usersSerivce);
	     
	    }
	
}
