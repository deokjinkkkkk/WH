package co.admin.wh.member.vo;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String id;
	private String pass;
	private String name;
	private String gender;
	private String tel;
	private String email;
	private String perm;
	private String loginWay;
	private String token;
	private String reToken;
	private int state;

	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String id, String pass, String name,
			String gender, String tel, String email, String perm, String loginWay, String token, String reToken ,int state) {
		super();
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.gender = gender;
		this.tel = tel;
		this.email = email;
		this.perm = perm;
		this.loginWay = loginWay;
		this.token = token;
		this.reToken = reToken;
		this.state = state;
	}

	public OAuthAttributes() {
		
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		if (registrationId.equals("kakao")) {
			return ofKakao(userNameAttributeName, attributes);
		}
		return ofNaver(userNameAttributeName, attributes);

	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes2) {
		// TODO Auto-generated method stub
		return null;
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
	        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");  // 카카오로 받은 데이터에서 계정 정보가 담긴 kakao_account 값을 꺼낸다.
	        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");   // 마찬가지로 profile(nickname, image_url.. 등) 정보가 담긴 값을 꺼낸다.
	        String gender = (String) kakao_account.get("gender");
	        System.out.println(gender +"++++++++++++++++++++++++++++");
	        if(gender == "male" ) {
	        	gender = "여";
	        }else {
	        	gender ="님";
	        }
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String pass = passwordEncoder.encode("1234");
	       System.out.println(attributes+"################");
	       System.out.println(userNameAttributeName+"################");
	        String loginWay = "K";
	        String token = "";
	        String retoken = "";
	        String phone = "";
	        int state = 0;
	        return new OAuthAttributes(
	        		attributes,
	        		userNameAttributeName,
	        		(String) kakao_account.get("email"),
	        		pass,
	        		(String) profile.get("nickname"),
	        		gender,
	        		phone,
	        		(String) kakao_account.get("email"),
	        		"USER",
	        		loginWay,
	        		token,
	        		retoken	,
	        		state
	        			
	       );
	                   
	  }

//	  private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
//	        Map<String, Object> response = (Map<String, Object>) attributes.get("response");    // 네이버에서 받은 데이터에서 프로필 정보다 담긴 response 값을 꺼낸다.
//	        
//	        return new OAuthAttributes(attributes,
//	                userNameAttributeName,
//	                (String) response.get("name"),
//	                (String) response.get("email"),
//	                (String) response.get("profile_image"));
//	    }

}
