package co.admin.wh.member.vo;

import lombok.Data;

@Data
public class KakaoOAuthToken {
	private String accessToken;
	private String tokenType;
	private String refreshToken;
	private String idToken;
	private String expiresIn;
	private String scope;
	private String refreshTokenExpiresIn;
	
}
