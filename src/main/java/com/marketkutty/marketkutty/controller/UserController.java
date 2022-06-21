package com.marketkutty.marketkutty.controller;

import com.marketkutty.marketkutty.model.dto.requestDto.RefreshTokenDto;
import com.marketkutty.marketkutty.model.dto.requestDto.SignInDto;
import com.marketkutty.marketkutty.model.dto.requestDto.SignupDto;
import com.marketkutty.marketkutty.model.dto.responseDto.UserLoginRespDto;
import com.marketkutty.marketkutty.model.dto.responseDto.UserRegisterRespDto;
import com.marketkutty.marketkutty.model.dto.responseDto.UserTokenRespDto;
import com.marketkutty.marketkutty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    // 토큰 테스트
    @GetMapping("/user/tokentest")
    public UserTokenRespDto tokenTest(@RequestHeader("Authorization") String authorization) {
        if(!authorization.startsWith("Bearer "))
            return new UserTokenRespDto(false, "토큰 인증 방식이 Bearer 가 아닙니다.");

        String token = authorization.substring(7, authorization.length() - 1);

        return userService.tokenTest(token);
    }


    // 토큰 재발급 받을때 사용
    @PostMapping("/user/refresh")
    public UserLoginRespDto tokenRefresh(@RequestBody RefreshTokenDto Dto) {
        return userService.verifyRefreshToken(Dto.getAccessToken(), Dto.getRefreshToken());
    }


    // 회원 로그인 페이지
    @PostMapping ("/user/login")
    public String login() {

        return "login";
    }

    // 로그인 요청 처리
    @PostMapping("/api/user/login")
    public UserLoginRespDto login(@RequestBody SignInDto Dto) throws NoSuchAlgorithmException {

        return  userService.loginUser(Dto);
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String signup() {

        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/api/user/signup")
    public UserRegisterRespDto registerUser(@RequestBody SignupDto Dto) throws NoSuchAlgorithmException {

        return userService.registerUser(Dto);
    }

    // ID 중복 확인
    @ResponseBody
    @GetMapping("/api/user/signinup/checkId/{username}")
    public Boolean usernameDupCheck(@PathVariable String username) {

        return userService.checkUsernameDuplicate(username);
    }

    // Email 중복 확인
    @ResponseBody
    @GetMapping("/api/user/signinup/checkEmail/{email}")
    public Boolean emailDupCheck(@PathVariable String email) {

        return userService.checkEmailDuplicate(email);
    }

}