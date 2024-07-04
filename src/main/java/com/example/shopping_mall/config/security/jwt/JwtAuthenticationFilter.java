package com.example.shopping_mall.config.security.jwt;

import com.example.shopping_mall.common.ResultCodeType;
import com.example.shopping_mall.common.response.Common;
import com.example.shopping_mall.common.response.CommonResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            // 1. Request Header에서 JWT 토큰 추출
            String token = resolveToken((HttpServletRequest) request);

            // 2. validateToken으로 토큰 유효성 검사
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            //토큰의 유효기간 만료
            setErrorResponse((HttpServletResponse) response, ResultCodeType.JWT_TOKEN_EXPIRED);
        } catch (JwtException | IllegalArgumentException e) {
            //유효하지 않은 토큰
            setErrorResponse((HttpServletResponse) response, ResultCodeType.JWT_TOKEN_ERROR);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ResultCodeType resultCodeType) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        Common common = new Common(resultCodeType);
        CommonResponseDto<Object> objectCommonResponseDto = new CommonResponseDto<>(common);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(objectCommonResponseDto));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Request Header에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}