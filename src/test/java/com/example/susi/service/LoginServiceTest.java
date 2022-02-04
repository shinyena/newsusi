package com.example.susi.service;

import com.example.susi.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {
    @Autowired
    LoginService loginService;

    @Test
    void login() {
        MemberDTO memberDTO = MemberDTO.builder()
                .email("user10@gmail.com")
                .password("1111")
                .build();
        boolean login = loginService.login(memberDTO);
        System.out.println("login = " + login);
    }

}