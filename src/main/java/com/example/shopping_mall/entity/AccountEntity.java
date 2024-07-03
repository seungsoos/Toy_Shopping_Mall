package com.example.shopping_mall.entity;

import com.example.shopping_mall.dto.account.request.SignupRequestDto;
import com.example.shopping_mall.entity.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "tb_account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity extends BaseEntity implements UserDetails{

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

    @Builder
    public AccountEntity(String loginId, String password, AccountType accountType) {
        this.loginId = loginId;
        this.password = password;
        this.accountType = accountType;
    }

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

    public void settingProductEntity(ProductEntity productEntity) {
        this.productEntity.add(productEntity);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add((GrantedAuthority) () -> this.accountType.toString());
        return collection;
    }

    @Override
    public String getUsername() {
        return this.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

