package com.example.susi.repository.admin;

import com.example.susi.entity.admin.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, String> {
}
