package com.example.managestaff.model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private String name;
    private int gender;
    private String dob;
    private String phoneNumber;
    private String email;
    private int departmentId;
    private int positionId;
}
