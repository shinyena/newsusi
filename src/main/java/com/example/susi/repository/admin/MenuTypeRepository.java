package com.example.susi.repository.admin;

import com.example.susi.entity.admin.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuTypeRepository extends JpaRepository<MenuType, Long> {
    @Query("select mt from MenuType mt where mt.type = :type")
    MenuType findMenuTypeByType(String type);
}
