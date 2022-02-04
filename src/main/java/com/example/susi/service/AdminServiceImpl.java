package com.example.susi.service;

import com.example.susi.dto.admin.InformationDTO;
import com.example.susi.dto.admin.MenuDTO;
import com.example.susi.entity.admin.DayOff;
import com.example.susi.entity.admin.Information;
import com.example.susi.entity.admin.Menu;
import com.example.susi.entity.admin.MenuType;
import com.example.susi.repository.admin.DayOffRepository;
import com.example.susi.repository.admin.InformationRepository;
import com.example.susi.repository.admin.MenuRepository;
import com.example.susi.repository.admin.MenuTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.PushBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final DayOffRepository dayOffRepository;
    private final InformationRepository informationRepository;
    private final MenuRepository menuRepository;
    private final MenuTypeRepository menuTypeRepository;


    /** DayOff Service */
    @Override
    public void registerDayOff(DayOff dayOff) {
        dayOffRepository.save(dayOff);
    }

    @Override
    public List<DayOff> getDayOff() {
        List<DayOff> dayOffList = dayOffRepository.findAll();
        return dayOffList;
    }

    @Override
    public void removeDayOff(DayOff dayOff) {
        dayOffRepository.deleteById(dayOff.getDate());
    }


    /** Information Service */
    @Override
    public InformationDTO getInformation(String adminId) {
        Optional<Information> byId = informationRepository.findById(adminId);
        Information information = byId.get();
        InformationDTO informationDTO = informationToDto(information);
        return informationDTO;
    }

    @Override
    public void modifyInformation(InformationDTO informationDTO) {
        Optional<Information> byId = informationRepository.findById(informationDTO.getAdminId());
        Information information = byId.get();
        information.changePassword(informationDTO.getPassword());
        information.changeLocation(informationDTO.getLocation());
        information.changeOpen(informationDTO.getOpen());
        information.changeClose(informationDTO.getClose());
        information.changeInstagram(informationDTO.getInstagram());
        information.changeCall(informationDTO.getPhone());
        information.changeTitle1(informationDTO.getTitle1());
        information.changeTitle2(informationDTO.getTitle2());
        information.changeTitle3(informationDTO.getTitle3());
        information.changeContent1(informationDTO.getContent1());
        information.changeContent2(informationDTO.getContent2());
        information.changeContent3(informationDTO.getContent3());
        information.changeNotice(informationDTO.getNotice());
        informationRepository.save(information);
    }


    /** Menu Service */
    @Override
    public void registerMenu(MenuDTO menuDTO) {
        Menu menu = dtoToMenu(menuDTO);
        menuRepository.save(menu);
    }

    @Override
    public List<MenuDTO> getAllMenu() {
        List<Menu> menuList = menuRepository.findAll();
        List<MenuDTO> dtoList = new ArrayList<>();
        menuList.forEach(menu -> {
            MenuDTO dto = menuToDto(menu);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public MenuDTO getOneMenu(Long menuId) {
        Optional<Menu> byId = menuRepository.findById(menuId);
        Menu menu = byId.get();
        MenuDTO dto = menuToDto(menu);
        return dto;
    }

    @Override
    public void modifyMenu(@RequestBody MenuDTO menuDTO) {
        Optional<Menu> byId = menuRepository.findById(menuDTO.getMenuId());
        Menu menu = byId.get();
        menu.changeMenuName(menuDTO.getMenuName());
        menu.changeMenuComment(menuDTO.getMenuComment());
        menu.changeMenuPrice(menuDTO.getMenuPrice());
        menu.changeMenuType(dtoToMenu(menuDTO).getMenuType());
        menuRepository.save(menu);
    }

    @Override
    public void removeMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    /** MenuType Service */
    @Override
    public List<MenuType> getMenuType() {
        List<MenuType> menuTypeList = menuTypeRepository.findAll();
        return menuTypeList;
    }

}
