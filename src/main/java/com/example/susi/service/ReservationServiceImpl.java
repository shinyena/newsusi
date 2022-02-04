package com.example.susi.service;

import com.example.susi.dto.ReservationDTO;
import com.example.susi.entity.Member;
import com.example.susi.entity.Reservation;
import com.example.susi.entity.ReserveTime;
import com.example.susi.entity.ReserveTimeID;
import com.example.susi.repository.MemberRepository;
import com.example.susi.repository.ReservationRepository;
import com.example.susi.repository.ReserveTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository repository;
    private final MemberRepository memberRepository;
    private final ReserveTimeRepository timeRepository;

    @Transactional
    @Override
    public void register(ReservationDTO reservationDTO) {
        Optional<ReserveTime> result = timeRepository.findById(new ReserveTimeID(reservationDTO.getRdate(), reservationDTO.getRtime()));

        if (result.isEmpty()) {
            Map<String, Object> entityMap = dtoToEntity(reservationDTO);

            Optional<Member> byId = memberRepository.findById(reservationDTO.getEmail());
            if (byId.isEmpty()) {
                Member member = (Member) entityMap.get("member");
                memberRepository.save(member);
            }


            ReserveTime reserveTime = (ReserveTime) entityMap.get("reserveTime");
            timeRepository.save(reserveTime);

            Reservation reservation = (Reservation) entityMap.get("reservation");
            repository.save(reservation);
        } else {
            log.error("이미 예약된 날짜, 시간 입니다.");
            log.error(result);
        }
    }

    @Modifying
    @Transactional
    @Override
    public void modify(ReservationDTO reservationDTO) {
        Optional<Reservation> result = repository.findById(reservationDTO.getRid());

        if (result.isPresent()) {
            Reservation reservation = result.get();
            reservation.changeName(reservationDTO.getName());
            reservation.changePhone(reservationDTO.getPhone());
            reservation.changeCount(reservationDTO.getCount());
            reservation.changeMessage(reservationDTO.getMessage());
            repository.save(reservation);

            Optional<ReserveTime> checkTime = timeRepository.findById(new ReserveTimeID(
                    reservationDTO.getRdate(), reservationDTO.getRtime()));
            if (checkTime.isEmpty()) {
                timeRepository.deleteById(new ReserveTimeID(
                        reservation.getReserveTime().getRdate(),
                        reservation.getReserveTime().getRtime()));

                ReserveTime reserveTime = ReserveTime.builder()
                        .rdate(reservationDTO.getRdate())
                        .rtime(reservationDTO.getRtime())
                        .build();
                timeRepository.save(reserveTime);

                reservation.changeTime(reserveTime);
                repository.save(reservation);
            }
            else {
                log.error("이미 예약된 날짜, 시간 입니다.");
                log.error(checkTime);
            }
        }
    }

    @Transactional
    @Override
    public void remove(Long rid) {
        ReserveTime reserveTime = repository.getReserveTimeByRid(rid);
        timeRepository.deleteById(new ReserveTimeID(reserveTime.getRdate(), reserveTime.getRtime()));
        repository.deleteById(rid);
    }

    @Transactional
    @Override
    public List<ReservationDTO> getAll() {
        List<Reservation> reservationList = repository.findAllOrderByReserveTime();
        List<ReservationDTO> dtoList = new ArrayList<>();
        reservationList.forEach( reservation -> {
            ReservationDTO dto  = entityToDTO(reservation);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Transactional
    @Override
    public List<ReservationDTO> getList(String email) {
        List<Reservation> reservationList = repository.findByEmail(email);
        List<ReservationDTO> dtoList = new ArrayList<>();
        reservationList.forEach( reservation -> {
            ReservationDTO dto  = entityToDTO(reservation);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Transactional
    @Override
    public ReservationDTO getOne(Long rid) {
        Optional<Reservation> result = repository.findById(rid);
        if (result.isPresent()) {
            Reservation reservation = result.get();
            return entityToDTO(reservation);
        }
        return null;
    }

    @Override
    public List<ReservationDTO> getTime(LocalDate date) {
        List<ReservationDTO> dtoList = new ArrayList<>();
        List<Reservation> reservationList = repository.findByDate(date);
        reservationList.forEach( reservation -> {
            ReservationDTO reservationDTO = entityToDTO(reservation);
            dtoList.add(reservationDTO);
        });
        return dtoList;
    }

    @Override
    public List<LocalDate> getDate() {
        List<LocalDate> dateList = timeRepository.getFullReservedDate();
        return dateList;
    }


}
