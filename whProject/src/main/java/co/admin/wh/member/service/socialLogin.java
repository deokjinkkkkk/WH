package co.admin.wh.member.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class socialLogin {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final KakaoOAuth2 kakaoOAuth2;
	private final AuthenticationManager authenticationManager;
	private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
	
	@Autowired
	public socialLogin(PasswordEncoder passwordEncoder,UserRepository userRepository,
			KakaoOAuth2 kakaoOAuth2,AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.kakaoOAuth2 = kakaoOAuth2;
		this.authenticationManager = authenticationManager;
		// TODO Auto-generated constructor stub
	}
	public void kakaoLogin(String authrizedCode) {
		 KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
	        Long kakaoId = userInfo.getId();
	        String nickname = userInfo.getNickname();
	        String email = userInfo.getEmail();

	}
}
