package com.example.shopping_mall.dto.account.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequestDto {

    private String refreshToken;
}
