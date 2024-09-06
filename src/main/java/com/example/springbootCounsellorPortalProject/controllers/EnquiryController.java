package com.example.springbootCounsellorPortalProject.controllers;

import com.example.springbootCounsellorPortalProject.dto.ViewEnqFilterRequest;
import com.example.springbootCounsellorPortalProject.model.Enquiry;
import com.example.springbootCounsellorPortalProject.services.CounsellorService;
import com.example.springbootCounsellorPortalProject.services.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EnquiryController {

    private final EnquiryService enquiryService;
    private final CounsellorService counsellorService;

    public EnquiryController(EnquiryService enquiryService, CounsellorService counsellorService) {
        this.enquiryService = enquiryService;
        this.counsellorService = counsellorService;
    }

    @PostMapping("/filter-enq")
    public String viewFilterRequest(ViewEnqFilterRequest viewEnqFilterRequest,HttpServletRequest req,Model model){
        //get existing session
        HttpSession session = req.getSession(false);
        Long counsellorId = (Long) session.getAttribute("counsellorId");

        List<Enquiry> enqList = enquiryService.getEnquiresWithFilter(viewEnqFilterRequest, counsellorId);

        model.addAttribute("enquiries",enqList);
        return "viewEnqPage";
    }

    @GetMapping("/view-enquiries")
    public String viewEnquiries(HttpServletRequest req, Model model){

        //get existing session
        HttpSession session = req.getSession(false);
        Long counsellorId = (Long) session.getAttribute("counsellorId");
        List<Enquiry> allEnquiries = enquiryService.getAllEnquiries(counsellorId);

        model.addAttribute("enquiries",allEnquiries);

        ViewEnqFilterRequest enqFilter = new ViewEnqFilterRequest();
        model.addAttribute("viewEnqFilterRequest",enqFilter);

        return "viewEnqPage";
    }


    @GetMapping("/enquiry")
    public String addEnquiry(Model model){

        Enquiry enquiryObj = new Enquiry();
        model.addAttribute("enqObj",enquiryObj);

        return "enquiryForm";
    }

    @GetMapping("/editEnq")
    public String updateEnquiry(@RequestParam("enqId") Long enqId,Model model){

        Enquiry enquiryObj = enquiryService.getEnquriyById(enqId);
        model.addAttribute("enqObj",enquiryObj);
        return "enquiryForm";
    }


    @PostMapping("/addEnquiry")
    public String handleAddEnquiry(@ModelAttribute("enqObj") Enquiry enqObj, HttpServletRequest httpServletReq, Model model){

        //get existing session
        HttpSession session = httpServletReq.getSession(false);
        Long counsellorId = (Long) session.getAttribute("counsellorId");

        boolean isSaved = enquiryService.addEnquiry(enqObj, counsellorId);
        if(isSaved){
            //success
            model.addAttribute("smsg","Successfully Saved....!");
        }else{
            model.addAttribute("emsg","Failed To Add The Enquiry!");
        }

        return "enquiryForm";
    }

}