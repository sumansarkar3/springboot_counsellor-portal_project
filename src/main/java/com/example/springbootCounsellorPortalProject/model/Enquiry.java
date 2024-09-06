package com.example.springbootCounsellorPortalProject.model;

import com.example.springbootCounsellorPortalProject.enums.ClassMode;
import com.example.springbootCounsellorPortalProject.enums.Course;
import com.example.springbootCounsellorPortalProject.enums.EnquiryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@Entity
@Table(name = "enquiries_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enqId;

    private String studentName;
    private String studentPhno;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_name")
    private Course courseName;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_mode")
    private ClassMode classMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "enq_status")
    private EnquiryStatus enqStatus;

    @CreationTimestamp
    private LocalDate createdDate;
    @UpdateTimestamp
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "counsellorId")
    private Counsellor counsellor;

}