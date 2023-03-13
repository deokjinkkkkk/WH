package co.admin.wh.member.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.member.vo.OAuthAttributes;
@Service
public class SocialUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private HttpSession httpSession;
		
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		OAuthAttributes attributes =OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		MemberVO member = saveOrUpdate(attributes);
		
		httpSession.setAttribute("user", new SessionUser(member));
		System.out.println(attributes.getAttributes()+"+++++++++++++++++++++++");
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(attributes.getPerm()))
                ,attributes.getAttributes()
                ,attributes.getNameAttributeKey());
	}
	
	 private MemberVO saveOrUpdate(OAuthAttributes attributes) {
	        MemberVO vo = new MemberVO(); 
	        vo.setId(attributes.getId());
	        vo.setPass(attributes.getPass());
	        vo.setName(attributes.getName());
	        vo.setGender(attributes.getGender());
	        vo.setTel(attributes.getTel());
	        vo.setEmail(attributes.getEmail());
	        vo.setPerm(attributes.getPerm());
	        vo.setLoginWay(attributes.getLoginWay());
	        vo.setToken(attributes.getToken());
	        vo.setReToken(attributes.getReToken());
	        vo.setState(attributes.getState());
	        
	        System.out.println(vo.getId());
	        MemberVO mvo = new MemberVO();
	        mvo = memberMapper.memberSelect(vo);
	        
	        if(mvo == null) {
	        	memberService.memberInsert(vo);
	        }
	        mvo = memberMapper.memberSelect(vo);
	        
	        return mvo;
	    }

}