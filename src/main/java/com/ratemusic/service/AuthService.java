package com.ratemusic.service;

import com.ratemusic.dto.LoginRequest;
import com.ratemusic.dto.LoginResponse;
import com.ratemusic.entity.User;
import com.ratemusic.exception.NotFoundException;
import com.ratemusic.repository.UserRepository;
import com.ratemusic.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 이메일입니다."));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponse(jwtUtil.generateToken(user.getId(), user.getEmail()));
    }
}
