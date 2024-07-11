package com.example.shopping_mall.service;

import com.example.shopping_mall.config.security.jwt.JwtToken;
import com.example.shopping_mall.dto.account.request.AccountDeleteDto;
import com.example.shopping_mall.dto.account.request.AccountLoginDto;
import com.example.shopping_mall.dto.account.request.AccountSignupDto;
import com.example.shopping_mall.dto.account.request.TokenRequestDto;
import com.example.shopping_mall.dto.account.response.TokenResponseDto;


public interface AccountService {

    JwtToken login(AccountLoginDto accountLoginDto);

    void signup(AccountSignupDto accountSignupDto);

    void delete(AccountDeleteDto deleteRequestDto);

    TokenResponseDto refreshToken(TokenRequestDto tokenRequestDto);
}
