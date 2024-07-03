package com.example.shopping_mall.dto.account.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountLoginDto {

    private String loginId;
    private String password;
}
