package com.example.susi.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationDTO {
    private String adminId;
    private String password;
    private String location;
    private String open;
    private String close;
    private String instagram;
    private String phone;
    private String title1;
    private String content1;
    private String title2;
    private String content2;
    private String title3;
    private String content3;
    private String notice;
}
