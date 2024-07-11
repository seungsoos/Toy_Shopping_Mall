package com.example.shopping_mall.controller;

import com.example.shopping_mall.config.security.jwt.JwtToken;
import com.example.shopping_mall.dto.account.request.AccountDeleteDto;
import com.example.shopping_mall.dto.account.request.AccountLoginDto;
import com.example.shopping_mall.dto.account.request.AccountSignupDto;
import com.example.shopping_mall.dto.account.request.TokenRequestDto;
import com.example.shopping_mall.dto.account.response.TokenResponseDto;
import com.example.shopping_mall.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/login")
    public JwtToken login(@RequestBody AccountLoginDto accountLoginDto) {
        return accountService.login(accountLoginDto);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody AccountSignupDto accountSignupDto) {
        accountService.signup(accountSignupDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody AccountDeleteDto deleteRequestDto) {
        accountService.delete(deleteRequestDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refreshToken(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenResponseDto tokenResponseDto = accountService.refreshToken(tokenRequestDto);
        return ResponseEntity.ok().body(tokenResponseDto);
    }
}
