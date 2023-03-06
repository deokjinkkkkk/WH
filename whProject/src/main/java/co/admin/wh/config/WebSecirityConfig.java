package co.admin.wh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
		return new UsersService();
	}
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
//				.anyRequest().authenticated()
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

	
	
}
