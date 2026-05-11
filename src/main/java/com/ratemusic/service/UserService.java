package com.ratemusic.service;

import com.ratemusic.dto.SignUpRequest;
import com.ratemusic.entity.User;
import com.ratemusic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest request){
        //이메일 중복 체크
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
        //닉네임 중복 체크
        if(userRepository.findByNickname(request.getNickname()).isPresent()){
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }
        //성공 로직
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getNickname());
        userRepository.save(user);
    }
}
