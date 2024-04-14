package ru.me.Ors.dto;

public class FacultyDepartmentDTO {
    private String facultyName;
    private String facultyDean;
    private String departmentName;
    private String departmentChairperson;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyDean() {
        return facultyDean;
    }

    public void setFacultyDean(String facultyDean) {
        this.facultyDean = facultyDean;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentChairperson() {
        return departmentChairperson;
    }

    public void setDepartmentChairperson(String departmentChairperson) {
        this.departmentChairperson = departmentChairperson;
    }
}
