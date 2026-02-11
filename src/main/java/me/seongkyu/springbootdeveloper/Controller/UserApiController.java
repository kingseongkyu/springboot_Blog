package me.seongkyu.springbootdeveloper.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.dto.AddUserRequest;
import me.seongkyu.springbootdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);  //회원가입 메서드 호출
        return "redirect:/login";   //회원가입이 완료된 이후에 로그인 페이지 이동

    }

    @GetMapping("/logout")  //logout GET 요청 -> SCLH의 logout 메서드를 호출하여 로그아웃
    public String logout(HttpServletRequest request, HttpServletResponse response)  {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }


}
