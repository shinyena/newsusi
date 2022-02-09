package com.example.susi.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private Long menuId;
    private String menuName;
    private String menuComment;
    private int menuPrice;
    private Long menuTypeId;
    private String menuType;
}
