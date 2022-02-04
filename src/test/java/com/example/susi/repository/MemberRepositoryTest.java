//package com.example.susi.repository;
//
//import com.example.susi.entity.Member;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
////    @Test
////    void insertAdmin() {
////        Member member = Member.builder()
////                .email("admin")
////                .password("1111")
////                .build();
////        memberRepository.save(member);
////    }
//
////    @Test
////    void insertMembers() {
////        IntStream.rangeClosed(1, 10).forEach(i -> {
////            Member member = Member.builder()
////                    .email("user" + i + "@gmail.com")
////                    .password("1111")
////                    .build();
////            memberRepository.save(member);
////        });
////    }
//
//    @Test
//    void findPassword() {
//        Member member = memberRepository.findByEmail("user1@gmail.com");
//        System.out.println("member.getPassword() = " + member.getPassword());;
//    }
//
//}