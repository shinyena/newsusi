package com.example.susi.service;

import com.example.susi.dto.MemberDTO;
import com.example.susi.dto.admin.InformationDTO;
import com.example.susi.entity.Member;
import com.example.susi.entity.admin.Information;
import com.example.susi.repository.MemberRepository;
import com.example.susi.repository.admin.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final InformationRepository informationRepository;

    public boolean login(MemberDTO memberDTO) {
        Member member = memberRepository.findByEmail(memberDTO.getEmail());
        if (member == null) {
            return false;
        }
        if (!member.getPassword().equals(memberDTO.getPassword())) {
            return false;
        }
        return true;
    }

    public boolean isAdmin(MemberDTO memberDTO) {
        if (memberDTO.getEmail().equals("admin") && memberDTO.getPassword().equals(memberRepository.getAdminPassword()))
            return true;
        else
            return false;
    }

    public boolean amdinLogin(InformationDTO informationDTO) {
        Optional<Information> byId = informationRepository.findById(informationDTO.getAdminId());
        if (byId.isPresent()) {
            Information information = byId.get();
            if (information.getPassword().equals(informationDTO.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
