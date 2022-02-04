package com.example.susi.controller;

import com.example.susi.annotation.UserLoginCheck;
import com.example.susi.dto.ReservationDTO;
import com.example.susi.dto.admin.InformationDTO;
import com.example.susi.dto.admin.MenuDTO;
import com.example.susi.service.AdminService;
import com.example.susi.service.KakaoLoginService;
import com.example.susi.service.LoginService;
import com.example.susi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/susi")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService service;
    private final KakaoLoginService kakaoLoginService;
    private final AdminService adminService;

    @GetMapping("/kakao/authorize")
    public String kakaoAuthorize() {
        String url = "https://kauth.kakao.com/oauth/authorize" +
                "?response_type=code" +
                "&client_id=c524a7239cfdb94b1732cb913de178c9" +
                "&redirect_uri=http://192.168.0.3:8080/susi/kakao/login";
        return "redirect:" + url;
    }

    @GetMapping("/kakao/login")
    public String kakaoLogin(String code, HttpSession session) {
        System.out.println("code = " + code);
        String token = kakaoLoginService.getToken(code);
        Map<String, Object> userInfo = kakaoLoginService.getUserInfo(token);
        session.setAttribute("userId", userInfo.get("email"));
        session.setAttribute("userName", userInfo.get("name"));
        return "redirect:/susi/subpage#book-a-table";
    }

    @GetMapping("/mainpage")
    public void mainpage(Model model) {
        List<MenuDTO> menuList = adminService.getAllMenu();
        model.addAttribute("menuList", menuList);
        InformationDTO information = adminService.getInformation("sushicaptain");
        model.addAttribute("info", information);
    }

    @UserLoginCheck
    @GetMapping("/subpage")
    public void subpage(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("userName"));
        InformationDTO information = adminService.getInformation("sushicaptain");
        model.addAttribute("info", information);
    }

    @UserLoginCheck
    @GetMapping("/listpage")
    public void getList(HttpSession session, Model model) {
        String email = session.getAttribute("userId").toString();
        List<ReservationDTO> dtoList = service.getList(email);
        model.addAttribute("dtoList", dtoList);
        InformationDTO information = adminService.getInformation("sushicaptain");
        model.addAttribute("info", information);
    }

    @UserLoginCheck
    @PostMapping("/register")
    public String register(ReservationDTO reservationDTO, RedirectAttributes redirectAttributes) {
        System.out.println("reservationDTO = " + reservationDTO);
        service.register(reservationDTO);
        redirectAttributes.addFlashAttribute("msg", "예약이 완료되었습니다.");
        return "redirect:/susi/mainpage";
    }

    @UserLoginCheck
    @GetMapping({"/read", "/modify"})
    public void read(Long rid, Model model) {
        ReservationDTO dto = service.getOne(rid);
        model.addAttribute("dto", dto);
    }

    @UserLoginCheck
    @PostMapping("/modify")
    public String modify(ReservationDTO reservationDTO, RedirectAttributes redirectAttributes) {
        service.modify(reservationDTO);
        redirectAttributes.addFlashAttribute("msg", "예약이 변경 되었습니다.");
        return "redirect:/susi/listpage#specials";
    }

    @UserLoginCheck
    @GetMapping("/delete")
    public String remove(Long rid, RedirectAttributes redirectAttributes) {
        service.remove(rid);
        redirectAttributes.addFlashAttribute("msg", "예약이 삭제 되었습니다.");
        return "redirect:/susi/listpage#specials";
    }

    @GetMapping("/date")
    public ResponseEntity<List<LocalDate>> getDate() {
        List<LocalDate> dateList = service.getDate();
        return new ResponseEntity<>(dateList, HttpStatus.OK);
    }

    @PostMapping("/time")
    public ResponseEntity<List<ReservationDTO>> getTime(@RequestBody ReservationDTO reservationDTO) {
        List<ReservationDTO> dtoList = service.getTime(reservationDTO.getRdate());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
