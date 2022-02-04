package com.example.susi.repository.admin;

import com.example.susi.entity.admin.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DayOffRepository extends JpaRepository<DayOff, LocalDate> {
}
