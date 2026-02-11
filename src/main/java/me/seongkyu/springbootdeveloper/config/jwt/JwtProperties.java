package me.seongkyu.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") //자바 클래스에 프로피티값을 가져와서 사용하는 애너테이션
public class JwtProperties {
    private String issuer;  //yml에서 설정한 jwt.issuer값
    private String secretKey;   //jwt.secret_key값이 매핑
}
