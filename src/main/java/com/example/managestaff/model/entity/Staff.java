package com.example.managestaff.model.entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int id;
    private String fullName;
    private int gender;
    private Date dob;
    private String phoneNumber;
    private String email;
    private int departmentId;
    private int positionId;
    private String status;
    private  String imgUrl;


}
