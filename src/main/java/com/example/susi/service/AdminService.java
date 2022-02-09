package com.example.susi.service;

import com.example.susi.dto.admin.InformationDTO;
import com.example.susi.dto.admin.MenuDTO;
import com.example.susi.entity.admin.DayOff;
import com.example.susi.entity.admin.Information;
import com.example.susi.entity.admin.Menu;
import com.example.susi.entity.admin.MenuType;

import java.util.List;

public interface AdminService {
    void registerDayOff(DayOff dayOff);
    List<DayOff> getDayOff();
    void removeDayOff(DayOff dayOff);
    InformationDTO getInformation(String adminId);
    void modifyInformation(InformationDTO informationDTO);
    void registerMenu(MenuDTO menuDTO);
    List<MenuDTO> getAllMenu();
    MenuDTO getOneMenu(Long menuId);
    void modifyMenu(MenuDTO oldMenu);
    void removeMenu(Long menuId);
    List<MenuType> getMenuType();


    default Information dtoToInformation(InformationDTO dto) {
        Information entity = Information.builder()
                .adminId(dto.getAdminId())
                .password(dto.getPassword())
                .location(dto.getLocation())
                .open(dto.getOpen())
                .close(dto.getClose())
                .instagram(dto.getInstagram())
                .phone(dto.getPhone())
                .title1(dto.getTitle1())
                .title2(dto.getTitle2())
                .title3(dto.getTitle3())
                .content1(dto.getContent1())
                .content2(dto.getContent2())
                .content3(dto.getContent3())
                .notice(dto.getNotice())
                .build();
        return entity;
    }

    default InformationDTO informationToDto(Information entity) {
        InformationDTO dto = InformationDTO.builder()
                .adminId(entity.getAdminId())
                .password(entity.getPassword())
                .location(entity.getLocation())
                .open(entity.getOpen())
                .close(entity.getClose())
                .instagram(entity.getInstagram())
                .phone(entity.getPhone())
                .title1(entity.getTitle1())
                .title2(entity.getTitle2())
                .title3(entity.getTitle3())
                .content1(entity.getContent1())
                .content2(entity.getContent2())
                .content3(entity.getContent3())
                .notice(entity.getNotice())
                .build();
        return dto;
    }

    default Menu dtoToMenu(MenuDTO dto, MenuType menuType) {
        Menu entity = Menu.builder()
                .menuId(dto.getMenuId())
                .menuName(dto.getMenuName())
                .menuComment(dto.getMenuComment())
                .menuPrice(dto.getMenuPrice())
                .menuType(menuType)
                .build();
        return entity;
    }

    default MenuDTO menuToDto(Menu entity) {
        MenuDTO dto = MenuDTO.builder()
                .menuId(entity.getMenuId())
                .menuName(entity.getMenuName())
                .menuComment(entity.getMenuComment())
                .menuPrice(entity.getMenuPrice())
                .menuType(entity.getMenuType().getType())
                .build();
        return dto;
    }

}
