package co.admin.wh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.admin.wh.member.service.LoginSuccessHandler;
import co.admin.wh.member.service.SocialSuccessHandler;
import co.admin.wh.member.service.SocialUserService;
import co.admin.wh.member.service.UsersService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecirityConfig {
	private final UsersService usersSerivce;

//	@Bean
//	public AuthenticationSuccessHandler successHandler() {
//		return new LoginSuccessHandler();
//	}

//	@Bean
//	public UserDetailsService UserDetailsService() {
//		return usersSerivce;
//	}
//	@Bean
//		public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	};
////	
	@Autowired
	private SocialUserService socialUserService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/login/**", "/login/kakao", "/memberSignUpForm", "/memberSignUp", "/memberIdChk","/disabled")
				.permitAll()
				// .anyRequest().permitAll()
				.anyRequest().authenticated())
				.formLogin(
						(form) -> form.loginPage("/login").loginProcessingUrl("/memberLogin").passwordParameter("pass")
								.usernameParameter("id").successHandler(new LoginSuccessHandler()).permitAll())
				.logout((logout) -> logout.permitAll().logoutUrl("/logout").logoutSuccessUrl("/main")).csrf().disable()

				.headers().frameOptions().sameOrigin().and().oauth2Login().loginPage("/login")
				.defaultSuccessUrl("/main").successHandler(new SocialSuccessHandler()).permitAll().userInfoEndpoint()
				.userService(socialUserService);

		return http.build();
	}



	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/scss/**",
				"/search/**", "/vendor/**");
	}

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(usersSerivce).passwordEncoder(new BCryptPasswordEncoder());

	}

}
