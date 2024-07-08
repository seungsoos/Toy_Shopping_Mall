package com.example.shopping_mall.service;

import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.exception.RootException;
import com.example.shopping_mall.config.security.jwt.JwtToken;
import com.example.shopping_mall.config.security.jwt.JwtTokenProvider;
import com.example.shopping_mall.dto.account.request.AccountDeleteDto;
import com.example.shopping_mall.dto.account.request.AccountLoginDto;
import com.example.shopping_mall.dto.account.request.AccountSignupDto;
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
    public JwtToken login(AccountLoginDto accountLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountLoginDto.getLoginId(), accountLoginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);

    }

    @Transactional
    public void signup(AccountSignupDto accountSignupDto) {
        validateSignUpRequest(accountSignupDto);

        String encodePassword = passwordEncoder.encode(accountSignupDto.getPassword());
        accountSignupDto.passwordEncoding(encodePassword);

        AccountEntity accountEntity = AccountEntity.toEntity(accountSignupDto);
        log.info("accountEntity = {}", accountEntity);

        accountRepository.save(accountEntity);
    }

    @Override
    @Transactional
    public void delete(AccountDeleteDto deleteRequestDto) {
        String loginId = deleteRequestDto.getLoginId();
        AccountEntity accountEntity = accountRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_4S000000));
        accountRepository.delete(accountEntity);
    }


    private void validateSignUpRequest(AccountSignupDto accountSignupDto) {

        if (accountRepository.existsByLoginId(accountSignupDto.getLoginId())) {
            throw new RootException(ResultCodeType.SERVER_ERROR_EXISTS_LOGIN_ID);
        }

        if (accountRepository.existsByEmail(accountSignupDto.getEmail())) {
            throw new RootException(ResultCodeType.SERVER_ERROR_EXISTS_EMAIL);
        }

        if (accountRepository.existsByNickName(accountSignupDto.getNickName())) {
            throw new RootException(ResultCodeType.SERVER_ERROR_EXISTS_NICK_NAME);
        }
    }
}
