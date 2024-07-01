package com.example.shopping_mall.entity;

import com.example.shopping_mall.dto.account.request.SignupRequestDto;
import com.example.shopping_mall.entity.enums.AccountType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "tb_account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(fetch = LAZY,cascade = ALL, mappedBy = "accountEntity", orphanRemoval = true)
    private List<ProductEntity> productEntity = new ArrayList<>();


    public AccountEntity(SignupRequestDto signupRequestDto) {
        this.loginId = signupRequestDto.getLoginId();
        this.email = signupRequestDto.getEmail();
        this.password = signupRequestDto.getPassword();
        this.nickName = signupRequestDto.getNickName();
        this.accountType = signupRequestDto.getAccountType();
    }

    public static AccountEntity toEntity(SignupRequestDto signupRequestDto) {
        return new AccountEntity(signupRequestDto);
    }
}

