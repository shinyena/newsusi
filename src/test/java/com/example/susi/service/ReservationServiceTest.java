//package com.example.susi.service;
//
//import com.example.susi.dto.ReservationDTO;
//import com.example.susi.entity.Reservation;
//import com.example.susi.entity.ReserveTime;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.test.annotation.Commit;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//class ReservationServiceTest {
//    @Autowired
//    private ReservationService reservationService;
//
////    @Commit
////    @Transactional
////    @Test
////    public void insertReservation() {
////        IntStream.rangeClosed(1, 10).forEach(i -> {
////            ReservationDTO reservationDTO = ReservationDTO.builder()
////                    .email("user" + i + "@gmail.com")
////                    .name("USER" + i)
////                    .phone("01012345678")
////                    .count(i)
////                    .rdate(LocalDate.of(2021, 01, i))
////                    .rtime("AM11")
////                    .build();
////            reservationService.register(reservationDTO);
////        });
////    }
//
////    @Commit
////    @Transactional
////    @Test
////    public void insertReservation2() {
////        ReservationDTO reservationDTO = ReservationDTO.builder()
////                .email("user18@gmail.com")
////                .name("USER18")
////                .phone("01012345678")
////                .count(2)
////                .rdate(LocalDate.of(2021, 01, 01))
////                .rtime("PM18")
////                .build();
////        reservationService.register(reservationDTO);
////    }
//
////    @Modifying
////    @Commit
////    @Transactional
////    @Test
////    public void modifyReservation() {
////        ReservationDTO reservationDTO = ReservationDTO.builder()
////                .rid(4L)
////                .count(2)
////                .rdate(LocalDate.of(2021, 02, 17))
////                .rtime("AM11")
////                .build();
////        reservationService.modify(reservationDTO);
////    }
//
////    @Commit
////    @Transactional
////    @Test
////    public void removeReservation() {
////        reservationService.remove(3L);
////    }
//
//    @Test
//    public void getAll() {
//        List<ReservationDTO> dtoList = reservationService.getAll();
//        dtoList.forEach( dto -> {
//            System.out.println(dto);
//        });
//    }
//
//    @Test
//    public void getOne() {
//        ReservationDTO result = reservationService.getOne(1L);
//        System.out.println("result = " + result);
//    }
//
//    @Commit
//    @Test
//    @Transactional
//    public void getTime() {
//        List<ReservationDTO> dtoList = reservationService.getTime(LocalDate.of(2022, 01, 26));
//        System.out.println("dtoList = " + dtoList);
//        dtoList.forEach( dto -> System.out.println("dto.getRtime() = " + dto.getRtime()));
//    }
//}