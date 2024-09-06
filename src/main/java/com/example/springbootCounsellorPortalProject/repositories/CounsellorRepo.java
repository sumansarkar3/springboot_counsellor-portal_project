package com.example.springbootCounsellorPortalProject.repositories;

import com.example.springbootCounsellorPortalProject.model.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounsellorRepo extends JpaRepository<Counsellor, Long> {
    Optional<Counsellor> findByEmailAndPwd(String email, String pwd);

    public Counsellor findByEmail(String email);
}
