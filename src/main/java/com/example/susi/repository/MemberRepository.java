package com.example.susi.repository;

import com.example.susi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);

    @Query("select password from Member where email = 'admin'")
    String getAdminPassword();
}
