//package com.example.susi.repository.admin;
//
//import com.example.susi.entity.admin.Menu;
//import com.example.susi.entity.admin.MenuType;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest
//class MenuRepositoryTest {
//    @Autowired
//    MenuRepository repository;
//
//    @Test
//    void insertMenu() {
//        Menu menu = Menu.builder()
//                .menuName("카이센동")
//                .menuType(MenuType.builder().typeId(1).build())
//
//                .build()
//        repository.save(menu)
//    }
//
//
//
//}