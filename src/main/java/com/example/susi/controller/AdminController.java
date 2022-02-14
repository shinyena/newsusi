package com.example.susi.controller;

import com.example.susi.annotation.AdminLoginCheck;
import com.example.susi.dto.ReservationDTO;
import com.example.susi.dto.admin.InformationDTO;
import com.example.susi.dto.admin.MenuDTO;
import com.example.susi.entity.admin.DayOff;
import com.example.susi.entity.admin.MenuType;
import com.example.susi.service.AdminService;
import com.example.susi.service.LoginService;
import com.example.susi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/susi/admin")
@RequiredArgsConstructor
public class AdminController implements ErrorController {
    private final ReservationService service;
    private final AdminService adminService;
    private final LoginService loginService;

    @GetMapping("/login")
    public String adminLoginPage(Model model, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        if (httpSession.getAttribute("adminId") != null) {
            redirectAttributes.addFlashAttribute("msg", "이미 로그인 되어있습니다!");
            return "redirect:/susi/admin/list";
        }
        else {
            model.addAttribute("noLogin", true);
        }
        return null;
    }

    @PostMapping("/login")
    public String adminLogin(InformationDTO informationDTO, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        System.out.println("informationDTO = " + informationDTO);
        if (loginService.amdinLogin(informationDTO)) {
            httpSession.setAttribute("adminId", informationDTO.getAdminId());
            return "redirect:/susi/admin/list";
        }
        else {
            redirectAttributes.addFlashAttribute("msg", "로그인 정보가 일치하지 않습니다!");
            return "redirect:/susi/admin/login";
        }
    }

    @AdminLoginCheck
    @GetMapping("/logout")
    public String logout(HttpSession httpSession, RedirectAttributes redirectAttributes) {
        httpSession.setAttribute("adminId", null);
        redirectAttributes.addFlashAttribute("msg", "로그아웃 되었습니다.");
        return "redirect:/susi/admin/login";
    }

    @AdminLoginCheck
    @GetMapping("/list")
    public void adminList(Model model) {
        List<ReservationDTO> reservationDTOList = service.getAll();
        model.addAttribute("dtoList", reservationDTOList);
    }

    @AdminLoginCheck
    @GetMapping("/read")
    public void read(Long rid, Model model) {
        ReservationDTO dto = service.getOne(rid);
        model.addAttribute("dto", dto);
    }

    @GetMapping("/delete")
    public String remove(Long rid, RedirectAttributes redirectAttributes) {
        service.remove(rid);
        redirectAttributes.addFlashAttribute("msg", "예약이 삭제 되었습니다.");
        return "redirect:/susi/admin/list";
    }

    @AdminLoginCheck
    @GetMapping("/menu")
    public void getAllMenu(Model model) {
        List<MenuDTO> dtoList = adminService.getAllMenu();
        model.addAttribute("dtoList", dtoList);
        List<MenuType> typeList = adminService.getMenuType();
        model.addAttribute("typeList", typeList);
    }

    @AdminLoginCheck
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuDTO> getOneMenu(@PathVariable Long menuId) {
        MenuDTO menuDTO = adminService.getOneMenu(menuId);
        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @AdminLoginCheck
    @PostMapping("/menu")
    public void registerMenu(@RequestBody MenuDTO menuDTO) {
        adminService.registerMenu(menuDTO);
    }

    @AdminLoginCheck
    @PutMapping("/menu")
    public void modifyMenu(@RequestBody MenuDTO menuDTO) {
        System.out.println("menuDTO = " + menuDTO);
        adminService.modifyMenu(menuDTO);
    }

    @AdminLoginCheck
    @DeleteMapping("/menu")
    public void removeMenu(@RequestBody MenuDTO menuDTO) {
        System.out.println("menuDTO = " + menuDTO);
        adminService.removeMenu(menuDTO.getMenuId());
    }

    @AdminLoginCheck
    @GetMapping("/dayOff")
    public void getDayOff(Model model) {
        List<DayOff> dayOffList = adminService.getDayOff();
        model.addAttribute("dayOffList", dayOffList);
    }

    @AdminLoginCheck
    @PostMapping("/dayOff")
    public void registerDayOff(DayOff dayOff) {
        adminService.registerDayOff(dayOff);
    }

    @AdminLoginCheck
    @DeleteMapping("/dayOff")
    public void removeDayOff(DayOff dayOff) {
        adminService.removeDayOff(dayOff);
    }

    @AdminLoginCheck
    @GetMapping("/information")
    public void getInformation(Model model) {
        InformationDTO dto = adminService.getInformation("sushicaptain");
        model.addAttribute("dto", dto);
    }

    @AdminLoginCheck
    @PostMapping("/information")
    public String modifyInformation(InformationDTO informationDTO, RedirectAttributes redirectAttributes) {
        log.info("informationDTO = " + informationDTO);
        adminService.modifyInformation(informationDTO);
        redirectAttributes.addFlashAttribute("msg", "저장되었습니다!");
        return "redirect:/susi/admin/information";
    }

    @AdminLoginCheck
    @GetMapping("/bannerImage")
    public void bannerImage() {


    }
}
