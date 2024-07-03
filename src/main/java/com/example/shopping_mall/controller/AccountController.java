package com.example.shopping_mall.controller;

import com.example.shopping_mall.config.security.jwt.JwtToken;
import com.example.shopping_mall.dto.account.request.LoginRequestDto;
import com.example.shopping_mall.dto.account.request.SignupRequestDto;
import com.example.shopping_mall.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/login")
    public JwtToken login(@RequestBody LoginRequestDto loginRequestDto) {
        return accountService.login(loginRequestDto);
    }


    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequestDto signupRequestDto) {
        accountService.signup(signupRequestDto);
    }


    @GetMapping("/{loginId}/id")
    public void findId(@PathVariable String loginId) {

    }


    @PostMapping("/{loginId}/password/reset")
    public void findPassword(@PathVariable String loginId) {

    }

}
