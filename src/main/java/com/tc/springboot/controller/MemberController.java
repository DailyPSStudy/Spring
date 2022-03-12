package com.tc.springboot.controller;
import com.tc.springboot.entity.User;
import com.tc.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController // JSON 형태 결과값을 반환해줌 (@ResponseBody가 필요없음)
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. (Autowired 역할)
@RequestMapping("/v1") // version1의 API
public class MemberController {
    private final UserRepository userRepository;
    @PostMapping("member")
    public User signUp() {
        final User member = User.builder() .tc_id("hi") .tc_password("test user") .tc_email("GG@GG.com").build(); return userRepository.save(member); }

}
