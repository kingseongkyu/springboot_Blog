package me.seongkyu.springbootdeveloper.repository;

import me.seongkyu.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);    //스프링 데이터 JPA가 자동구현
}

