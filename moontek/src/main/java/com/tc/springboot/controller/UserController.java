package com.tc.springboot.controller;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc.springboot.Dto.*;
import com.tc.springboot.Service.UserService;
import com.tc.springboot.entity.User;
import com.tc.springboot.jwt.JwtAuthenticationProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"유저관리 API"})
public class UserController {
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserService userService;

    @PostMapping("/v1/user/join")
    @ApiOperation(value = "회원가입")
    public int save(@RequestBody UserSaveRequestDto requestDto){
        return userService.save(requestDto);
    }

    @PutMapping("/v1/user/modify/{tc_id}")
    @ApiOperation(value = "회원정보수정")
    public int update(@PathVariable String tc_id, @RequestBody UserUpdateRequestDto requestDto){
        return userService.update(tc_id,requestDto);
    }

    @GetMapping("/v1/user/detail/{tc_id}")
    @ApiOperation(value = "마이페이지조회")
    public UserResponseDto findById(@PathVariable String tc_id){
        return userService.findById(tc_id);
    }

    @PostMapping("/v1/user/login")
    @JsonProperty("requestDto")
    @ApiOperation(value = "로그인")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto,HttpServletResponse response ){
       User user= userService.findPasswordById(requestDto);
       if(user==null){
           throw new IllegalArgumentException("잘못된 계정정보입니다.");
       }
        System.out.println("여기까지됨123" + user.getUsername() + user.getRoles());
        String token = jwtAuthenticationProvider.createToken(user.getUsername(), user.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new UserLoginResponseDto(user);
    }
    @PostMapping("/v1/user/logout")
    @ApiOperation(value = "로그아웃")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }



    @GetMapping("/v1/user/info")
    @ApiOperation(value = "마이페이지토큰이용")
    public UserResponseDto getInfo(HttpServletRequest request){
        System.out.println("Zzz");

        System.out.println(request.getHeader("X-AUTH-TOKEN") + "^.^");
        String token = request.getHeader("X-AUTH-TOKEN");
        System.out.println(token+ "^.^");
        String userpk = jwtAuthenticationProvider.getUserPk(token); //여기서 에러나면 없는것
        System.out.println(userpk + " sss");
        // Object details = SecurityContextHolder.getContext().getAuthentication();
       /* if(details == null) {
            System.out.println("비어있음");
        }
        else {
            System.out.println("안비어있어욤");
            System.out.println(details);
        }
        if(details != null && !(details instanceof  String)) return new UserLoginResponseDto((User) details);

        */
        System.out.println("반환값없음");
        return userService.findById(userpk);
    }


}
