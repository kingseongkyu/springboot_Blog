package me.seongkyu.springbootdeveloper.config.oauth;

import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.User;
import me.seongkyu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService { //OAuth 서비스에서 제공하는 정보로 유저 객체 만드는 loadUser를 사용해 사용자 객체 불러옴
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //요청을 바탕으로 유저 정보를 담은 객체 반환, loadUser 메서드로 사용자 조회
        OAuth2User user = super.loadUser(userRequest);  //사용자 정보가 있으면 업데이트
        saveOrUpdate(user);     //없으면 saveOrUpdate 실행해 users 테이블에 회원 데이트 추가
        return user;
    }

    //유저가 있으면 업데이트, 없으면 유저 생성
    private User saveOrUpdate(OAuth2User oAuth2User) {  //사용자가 user테이블에 있으면 업데이트 없으면 사용자를 새로 생성해서 데이터베이스에 저장
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(User.builder()
                        .email(email)
                        .nickname(name)
                        .build());
        return userRepository.save(user);
    }
}














