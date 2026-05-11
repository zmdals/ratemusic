package com.ratemusic.filter;

import com.ratemusic.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //1.헤더에서 토큰 꺼내기
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            //2. 토큰 유효성 검증
            if(jwtUtil.validateToken(token)){
                Long userId = jwtUtil.getUserId(token);

                //3. Security에 유저 정보 등록
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
                //Spring Security에게 "이 요청은 userId 누구누구가 보낸 요청이야" 라고 등록하는 과정.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //4. 다음 필터로 넘기기
        filterChain.doFilter(request,response);
    }
}
