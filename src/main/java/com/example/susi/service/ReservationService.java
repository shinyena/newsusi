package com.example.susi.service;

import com.example.susi.dto.MemberDTO;
import com.example.susi.dto.ReservationDTO;
import com.example.susi.entity.Member;
import com.example.susi.entity.Reservation;
import com.example.susi.entity.ReserveTime;
import com.example.susi.entity.admin.MenuType;
import com.example.susi.repository.admin.MenuTypeRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReservationService {
    void register(ReservationDTO reservationDTO);
    List<ReservationDTO> getAll();
    List<ReservationDTO> getList(String email);
    ReservationDTO getOne(Long rid);
    void modify(ReservationDTO reservationDTO);
    void remove(Long rid);
    List<ReservationDTO> getTime(LocalDate date);
    List<LocalDate> getDate();

    default Map<String, Object> dtoToEntity(ReservationDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();

        Member member = Member.builder()
                .email(dto.getEmail())
                .build();
        entityMap.put("member", member);

        ReserveTime reserveTime = ReserveTime.builder()
                .rdate(dto.getRdate())
                .rtime(dto.getRtime())
                .build();

        entityMap.put("reserveTime", reserveTime);

        Reservation reservation = Reservation.builder()
                .rid(dto.getRid())
                .member(member)
                .name(dto.getName())
                .phone(dto.getPhone())
                .count(dto.getCount())
                .reserveTime(reserveTime)
                .message(dto.getMessage())
                .build();
        entityMap.put("reservation", reservation);

        return entityMap;
    }

    default ReservationDTO entityToDTO(Reservation reservation) {
        ReservationDTO dto = ReservationDTO.builder()
                .rid(reservation.getRid())
                .email(reservation.getMember().getEmail())
                .name(reservation.getName())
                .phone(reservation.getPhone())
                .count(reservation.getCount())
                .rdate(reservation.getReserveTime().getRdate())
                .rtime(reservation.getReserveTime().getRtime())
                .message(reservation.getMessage())
                .build();
        return dto;
    }
}
