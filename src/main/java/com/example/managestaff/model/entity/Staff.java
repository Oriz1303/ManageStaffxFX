package com.example.managestaff.model.entity;

import javafx.collections.ObservableArray;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff implements ObservableArray<Staff> {
    private int id;
    private String name;
    private int gender;
    private Date dob;
    private String phoneNumber;
    private String email;
    private int departmentId;
    private int positionId;
    private String urlPortrait;
}
