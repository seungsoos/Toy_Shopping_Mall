package com.example.shopping_mall.dto.account.request;


import com.example.shopping_mall.entity.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSignupDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickName;
    @NotBlank
    private AccountType accountType;

    public void passwordEncoding(String encodingPassword){
        this.password = encodingPassword;
    }


}
