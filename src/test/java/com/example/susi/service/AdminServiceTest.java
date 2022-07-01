//package com.example.susi.service;
//
//import com.example.susi.dto.admin.InformationDTO;
//import com.example.susi.dto.admin.MenuDTO;
//import com.example.susi.entity.admin.Information;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class AdminServiceTest {
//    @Autowired
//    AdminService adminService;
//
//    @Test
//    void modifyInformation() {
//        adminService.modifyInformation(InformationDTO.builder()
//                        .adminId("sushicaptain")
//                        .location("인천 중구 햇내로안길 48-8")
//                        .build());
//    }
//
//    @Test
//    void getAllMenu() {
//        List<MenuDTO> allMenu = adminService.getAllMenu();
//        System.out.println("allMenu = " + allMenu);
//    }
//
//}