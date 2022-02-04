package com.example.susi.repository;

import com.example.susi.dto.ReservationDTO;
import com.example.susi.entity.ReserveTime;
import com.example.susi.entity.ReserveTimeID;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReserveTimeRepository extends JpaRepository<ReserveTime, ReserveTimeID> {
    @Query("select rdate from ReserveTime group by rdate having count(rdate) = 6")
    List<LocalDate> getFullReservedDate();
}
