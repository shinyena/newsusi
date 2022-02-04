package com.example.susi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@IdClass(ReserveTimeID.class)
public class ReserveTime {
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rdate;
    @Id
    private String rtime;
}
