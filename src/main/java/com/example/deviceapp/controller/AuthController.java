package com.example.deviceapp.controller;

import com.example.deviceapp.dto.auth.SignupRequest;
import com.example.deviceapp.dto.auth.UserInfoResponse;
import com.example.deviceapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoResponse signup(@RequestBody @Valid SignupRequest request) {
        return userService.signup(request);
    }

    /**
     * 현재 로그인한 사용자 정보
     * - HTTP Basic으로 로그인한 유저 기준
     */
    @GetMapping("/me")
    public UserInfoResponse me(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new IllegalStateException("인증 정보가 없습니다.");
        }

        String username = userDetails.getUsername();
        return userService.getUserInfoByUsername(username);
    }

}