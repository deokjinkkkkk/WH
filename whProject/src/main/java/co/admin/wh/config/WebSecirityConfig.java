package co.admin.wh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;
import lombok.Setter;

@Configuration
@EnableWebSecurity
public class WebSecirityConfig{
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.passwordParameter("password")
				.usernameParameter("id")
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
	public UserDetailsService userDetailsService() {
		
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("USER")
				.password("1234")
				.roles("ADMIN")
				.build();
		UserDetails admin =
				 User.withDefaultPasswordEncoder()
					.username("ADMIN")
					.password("1234")
					.roles("ADMIN")
					.build();
		System.out.println(admin.getPassword());
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	
}
