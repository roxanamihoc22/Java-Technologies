package com.example.StableMatch.dto;

public class AssignmentDTO {

    private String studentCode;
    private String courseCode;

    public AssignmentDTO() {}

    public AssignmentDTO(String studentCode, String courseCode) {
        this.studentCode = studentCode;
        this.courseCode = courseCode;
    }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
}

