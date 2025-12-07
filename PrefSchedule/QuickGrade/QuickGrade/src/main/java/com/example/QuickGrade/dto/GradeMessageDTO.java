package com.example.QuickGrade.dto;

public class GradeMessageDTO {

    private String studentCode;
    private String courseCode;
    private double grade;

    public GradeMessageDTO() {}

    public GradeMessageDTO(String studentCode, String courseCode, double grade) {
        this.studentCode = studentCode;
        this.courseCode = courseCode;
        this.grade = grade;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

}
