package com.example.springbootCounsellorPortalProject.repositories;

import com.example.springbootCounsellorPortalProject.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByCounsellor_CounsellorId(Long counsellorId);

    @Query(value="select * from enquiries_tbl where counsellor_id = :counsellorId",nativeQuery = true)
    public List<Enquiry> countTotalInquiries(Long counsellorId);

}