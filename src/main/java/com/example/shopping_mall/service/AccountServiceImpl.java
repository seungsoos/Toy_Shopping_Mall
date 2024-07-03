package com.example.shopping_mall.service;

import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.exception.RootException;
import com.example.shopping_mall.config.security.jwt.JwtToken;
import com.example.shopping_mall.config.security.jwt.JwtTokenProvider;
import com.example.shopping_mall.dto.account.request.LoginRequestDto;
import com.example.shopping_mall.dto.account.request.SignupRequestDto;
import com.example.shopping_mall.entity.AccountEntity;
import com.example.shopping_mall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public JwtToken login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getLoginId(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);

    }

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        validateSignUpRequest(signupRequestDto);

        String encodePassword = passwordEncoder.encode(signupRequestDto.getPassword());
        signupRequestDto.passwordEncoding(encodePassword);

        AccountEntity accountEntity = AccountEntity.toEntity(signupRequestDto);
        log.info("accountEntity = {}", accountEntity);

        accountRepository.save(accountEntity);
    }

    private void validateSignUpRequest(SignupRequestDto signupRequestDto) {

        if (accountRepository.existsByLoginId(signupRequestDto.getLoginId())) {
            throw new RootException(ResultCodeType.SERVER_ERROR_EXISTS_LOGIN_ID);
        }

        if (accountRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new RootException(ResultCodeType.SERVER_ERROR_EXISTS_EMAIL);
        }

        if (accountRepository.existsByNickName(signupRequestDto.getNickName())) {
            throw new RootException(ResultCodeType.SERVER_ERROR_EXISTS_NICK_NAME);
        }
    }
}
