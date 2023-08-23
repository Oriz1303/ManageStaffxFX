package com.example.managestaff.model.services;

public class SalarySearchModel {

    Integer id;
    String present;
    String absent;
    Integer staff_id;

    public SalarySearchModel(Integer id, String present, String absent, Integer staff_id) {
        this.id = id;
        this.present = present;
        this.absent = absent;
        this.staff_id = staff_id;
    }

    public Integer getId() {
        return id;
    }

    public String getPresent() {
        return present;
    }

    public String getAbsent() {
        return absent;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }
}
