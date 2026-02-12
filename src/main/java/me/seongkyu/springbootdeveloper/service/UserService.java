package me.seongkyu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.User;
import me.seongkyu.springbootdeveloper.dto.AddUserRequest;
import me.seongkyu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
        }

//        // 1) 이메일 중복 체크 (저장 전에 해야 함)
//        System.out.println("email=" + dto.getEmail());
//        System.out.println("password=" + dto.getPassword()); // null이면 여기서 바로 걸림
//
//        if (userRepository.existsByEmail(dto.getEmail())) {
//            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
//        }
//
//        // 2) 저장 (암호화 포함)
//        User user = User.builder()
//                .email(dto.getEmail())
//                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
//                .build();
//
//        return userRepository.save(user).getId();
//    }

    //전달받은 유저 ID로 유저를 검색해서 전달하는 findById() 메서드 추가
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {     //이메일을 입력받아 users 테이블에서 유저를 찾고 없으면 예외
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}












