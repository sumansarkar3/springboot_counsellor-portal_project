package com.example.springbootCounsellorPortalProject.services;

import com.example.springbootCounsellorPortalProject.dto.DashboardResponse;
import com.example.springbootCounsellorPortalProject.model.Counsellor;

import java.util.Optional;

public interface CounsellorService {

    public Counsellor findByEmail(String email);
    boolean register(Counsellor counsellor);
    Optional<Counsellor> login(String email, String pwd);
    DashboardResponse getDashboardInfo(Long counsellorId);
}
