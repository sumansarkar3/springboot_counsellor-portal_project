package com.example.springbootCounsellorPortalProject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@Entity
@Table(name = "counsellors_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Counsellor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counsellorId;

    private String name;
    @Column(unique = true)
    private String email;
    private String pwd;
    private String phno;
    @CreationTimestamp
    private LocalDate createdDate;
    @UpdateTimestamp
    private LocalDate updatedDate;


}