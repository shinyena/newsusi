package com.example.susi.repository;

import com.example.susi.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationRepositoryTest {
    @Autowired
    ReservationRepository repository;

    @Test
    void getListByMember() {
        List<Reservation> reservationList = repository.findByEmail("user1@gmail.com");
        System.out.println("reservationList = " + reservationList);
    }
}