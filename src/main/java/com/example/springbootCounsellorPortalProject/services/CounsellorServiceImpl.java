package com.example.springbootCounsellorPortalProject.services;


import com.example.springbootCounsellorPortalProject.dto.DashboardResponse;
import com.example.springbootCounsellorPortalProject.enums.EnquiryStatus;
import com.example.springbootCounsellorPortalProject.model.Counsellor;
import com.example.springbootCounsellorPortalProject.model.Enquiry;
import com.example.springbootCounsellorPortalProject.repositories.CounsellorRepo;
import com.example.springbootCounsellorPortalProject.repositories.EnquiryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    private final CounsellorRepo counsellorRepository;

    private final EnquiryRepo enquiryRepository;

    public CounsellorServiceImpl(CounsellorRepo counsellorRepository, EnquiryRepo enquiryRepository) {
        this.counsellorRepository = counsellorRepository;
        this.enquiryRepository = enquiryRepository;
    }

    @Override
    public Counsellor findByEmail(String email) {
        return counsellorRepository.findByEmail(email);
    }

    @Override
    public boolean register(Counsellor counsellor) {
        Counsellor counsellorSave = counsellorRepository.save(counsellor);
        if(counsellorSave.getCounsellorId()!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Optional<Counsellor> login(String email, String pwd) {
        return counsellorRepository.findByEmailAndPwd(email, pwd);
    }

    @Override
    public DashboardResponse getDashboardInfo(Long counsellorId) {

        DashboardResponse dashboardResponse = new DashboardResponse();

        List<Enquiry> enquiries = enquiryRepository.countTotalInquiries(counsellorId);
        int totalEnq = enquiries.size();

        int enrolledEnq = enquiries.stream().filter(e -> e.getEnqStatus() == EnquiryStatus.ENROLLED)
                .collect(Collectors.toList()).size();

        int newEnq = enquiries.stream().filter(e -> e.getEnqStatus()==EnquiryStatus.NEW)
                .collect(Collectors.toList()).size();

        int lostEnq = enquiries.stream().filter(e -> e.getEnqStatus() ==EnquiryStatus.LOST)
                .collect(Collectors.toList()).size();

        dashboardResponse.setTotalInquiries(totalEnq);
        dashboardResponse.setEnrolledInquiries(enrolledEnq);
        dashboardResponse.setNewInquiries(newEnq);
        dashboardResponse.setLostInquiries(lostEnq);

        return dashboardResponse;
    }

}