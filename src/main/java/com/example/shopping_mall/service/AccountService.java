package com.example.shopping_mall.service;

import com.example.shopping_mall.config.security.jwt.JwtToken;
import com.example.shopping_mall.dto.account.request.LoginRequestDto;
import com.example.shopping_mall.dto.account.request.SignupRequestDto;


public interface AccountService {


    JwtToken login(LoginRequestDto loginRequestDto);

    void signup(SignupRequestDto signupRequestDto);
}
