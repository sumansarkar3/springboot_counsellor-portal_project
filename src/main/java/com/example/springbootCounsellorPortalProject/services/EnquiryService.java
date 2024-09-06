package com.example.springbootCounsellorPortalProject.services;

import com.example.springbootCounsellorPortalProject.dto.ViewEnqFilterRequest;
import com.example.springbootCounsellorPortalProject.model.Enquiry;

import java.util.List;

public interface EnquiryService {
    boolean addEnquiry(Enquiry enq, Long counsellorId);
    List<Enquiry> getAllEnquiries(Long counsellorId);
    List<Enquiry> getEnquiresWithFilter(ViewEnqFilterRequest filterReq, Long counsellorId);
    Enquiry getEnquriyById(Long enqId);
}
