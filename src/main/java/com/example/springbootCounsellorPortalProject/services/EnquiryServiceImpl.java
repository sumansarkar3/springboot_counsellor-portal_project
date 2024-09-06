package com.example.springbootCounsellorPortalProject.services;

import com.example.springbootCounsellorPortalProject.dto.ViewEnqFilterRequest;
import com.example.springbootCounsellorPortalProject.model.Counsellor;
import com.example.springbootCounsellorPortalProject.model.Enquiry;
import com.example.springbootCounsellorPortalProject.repositories.CounsellorRepo;
import com.example.springbootCounsellorPortalProject.repositories.EnquiryRepo;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryServiceImpl implements EnquiryService{
    private final EnquiryRepo enquiryRepository;

    private final CounsellorRepo counsellorRepository;

    public EnquiryServiceImpl(EnquiryRepo enquiryRepository, CounsellorRepo counsellorRepository) {
        this.enquiryRepository = enquiryRepository;
        this.counsellorRepository = counsellorRepository;
    }

    @Override
    public boolean addEnquiry(Enquiry enq, Long counsellorId) {
        Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);
        if (counsellor != null) {
            enq.setCounsellor(counsellor);
            enquiryRepository.save(enq);
            return true;
        }
        return false;
    }

    @Override
    public List<Enquiry> getAllEnquiries(Long counsellorId) {
        return enquiryRepository.findByCounsellor_CounsellorId(counsellorId);
    }

    @Override
    public List<Enquiry> getEnquiresWithFilter(ViewEnqFilterRequest filterReq, Long counsellorId) {

//        //Query By Implementation (Dynamic query preparation)
//        Enquiry enquiry = new Enquiry();
//
//        if(StringUtils.isNotEmpty(filterReq.getClassMode().toString())){
//            enquiry.setClassMode(filterReq.getClassMode());
//        }
//
//        if(StringUtils.isNotEmpty(filterReq.getCourseName().toString())){
//            enquiry.setCourseName(filterReq.getCourseName());
//        }
//
//        if(StringUtils.isNotEmpty(filterReq.getEnquiryStatus().toString())){
//            enquiry.setEnqStatus(filterReq.getEnquiryStatus());
//        }
//
//        Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);
//        enquiry.setCounsellor(counsellor);
//
//        Example<Enquiry> of = Example.of(enquiry);  //It will perform Dynamic query
//
//        List<Enquiry> enqList = enquiryRepository.findAll(of);
//
//        return enqList;


        //Query By Implementation (Dynamic query preparation)
        // Create an empty Enquiry object to populate with filters
        Enquiry enquiry = new Enquiry();

        // Check if the filter has a non-null ClassMode and set it
        if(filterReq.getClassMode() != null){
            enquiry.setClassMode(filterReq.getClassMode());
        }

        // Check if the filter has a non-null CourseName and set it
        if(filterReq.getCourseName() != null){
            enquiry.setCourseName(filterReq.getCourseName());
        }

        // Check if the filter has a non-null EnquiryStatus and set it
        if(filterReq.getEnquiryStatus() != null){
            enquiry.setEnqStatus(filterReq.getEnquiryStatus());
        }

        // Retrieve the Counsellor object by ID
        Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);
        enquiry.setCounsellor(counsellor);

        // Create an Example instance to perform the dynamic query
        Example<Enquiry> of = Example.of(enquiry);  //It will perform Dynamic query

        // Retrieve and return the list of enquiries matching the filter criteria
        List<Enquiry> enqList = enquiryRepository.findAll(of);

        return enqList;

    }

    @Override
    public Enquiry getEnquriyById(Long enqId) {
        return enquiryRepository.findById(enqId).orElse(null);
    }

}
