package com.example.susi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"member", "reserveTime"})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    private String name;
    private String phone;
    private int count;
    @Column(length = 1000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private ReserveTime reserveTime;

    public void changeName(String name) {
        this.name = name;
    }
    public void changePhone(String phone) {
        this.phone = phone;
    }
    public void changeCount(int count) {
        this.count = count;
    }
    public void changeTime(ReserveTime reserveTime) {
        this.reserveTime = reserveTime;
    }
    public void changeMessage(String message) {
        this.message = message;
    }
}
