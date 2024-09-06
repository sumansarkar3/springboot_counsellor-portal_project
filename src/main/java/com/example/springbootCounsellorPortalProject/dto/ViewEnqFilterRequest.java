package com.example.springbootCounsellorPortalProject.dto;

import com.example.springbootCounsellorPortalProject.enums.ClassMode;
import com.example.springbootCounsellorPortalProject.enums.Course;
import com.example.springbootCounsellorPortalProject.enums.EnquiryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewEnqFilterRequest {

    private Course courseName;
    private ClassMode classMode;
    private EnquiryStatus enquiryStatus;
}
