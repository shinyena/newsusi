//package com.example.susi.repository.admin;
//
//import com.example.susi.entity.admin.Information;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//@SpringBootTest
//class InformationRepositoryTest {
//    @Autowired
//    InformationRepository repository;
//
//    @Test
//    void insertInformation() {
//        Information information = Information.builder()
//                .adminId("sushicaptain")
//                .password("1234")
//                .location("")
//                .open("")
//                .close("")
//                .instagram("")
//                .phone("")
//                .build();
//
//        repository.save(information);
//    }
//
//}