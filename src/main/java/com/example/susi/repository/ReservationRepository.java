package com.example.susi.repository;

import com.example.susi.dto.ReservationDTO;
import com.example.susi.entity.Reservation;
import com.example.susi.entity.ReserveTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select t from Reservation r" +
            " left outer join ReserveTime t on r.reserveTime = t" +
            " where r.rid = :rid")
    ReserveTime getReserveTimeByRid(Long rid);

    @Query("select r from Reservation r where r.reserveTime.rdate = :date")
    List<Reservation> findByDate(LocalDate date);

    @Query("select r from Reservation r where r.member.email = :email")
    List<Reservation> findByEmail(String email);

    @Query("select r from Reservation r order by r.reserveTime.rdate asc," +
            " r.reserveTime.rtime asc")
    List<Reservation> findAllOrderByReserveTime();
}
