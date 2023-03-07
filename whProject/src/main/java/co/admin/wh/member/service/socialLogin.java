package co.admin.wh.member.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class socialLogin {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final KakaoOAuth2 kakaoOAuth2;
	private final AuthenticationManager authenticationManager;
	private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
}
