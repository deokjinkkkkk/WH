package co.admin.wh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import co.admin.wh.member.service.DisabledAccountHandler;
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
				.antMatchers("companion/**","/myPage","/companion","/chat/**").hasAnyAuthority("USER","ADMIN")
				// .anyRequest().permitAll()
				.anyRequest().permitAll())
				.formLogin(
						(form) -> form.loginPage("/login").loginProcessingUrl("/memberLogin").passwordParameter("pass")
								.usernameParameter("id").successHandler(new LoginSuccessHandler()).failureHandler(new DisabledAccountHandler()).permitAll())
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
		web.httpFirewall(defaultHttpFirewall());
	}
	//암호화 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(usersSerivce).passwordEncoder(new BCryptPasswordEncoder());

	}
	
	
	//url 더블 슬래쉬 허용 
	@Bean
	public HttpFirewall defaultHttpFirewall() {
	    return new DefaultHttpFirewall();
	}


}
