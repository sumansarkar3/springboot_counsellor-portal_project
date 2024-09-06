package com.example.springbootCounsellorPortalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private Integer totalInquiries;
    private Integer newInquiries;
    private Integer enrolledInquiries;
    private Integer lostInquiries;

}
