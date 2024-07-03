package com.example.shopping_mall.config.security;

import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.exception.RootException;
import com.example.shopping_mall.entity.AccountEntity;
import com.example.shopping_mall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        return accountRepository.findByLoginId(loginId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new RootException(ResultCodeType.SERVER_ERROR_LOGIN_ID_NOT_EQUALS));
    }

    private UserDetails createUserDetails(AccountEntity accountEntity) {
        return User.builder()
                .username(accountEntity.getLoginId())
                .password(accountEntity.getPassword())
                .roles(accountEntity.getAccountType().toString())
                .build();
    }

}
