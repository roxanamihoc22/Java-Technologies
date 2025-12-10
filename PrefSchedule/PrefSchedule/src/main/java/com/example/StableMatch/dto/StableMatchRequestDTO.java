package com.example.StableMatch.dto;

import java.util.List;
import java.util.Map;

public class StableMatchRequestDTO {

    private List<String> students;
    private List<String> courses;

    private Map<String, List<String>> studentPreferences;
    private Map<String, List<String>> coursePreferences;

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public Map<String, List<String>> getStudentPreferences() {
        return studentPreferences;
    }

    public void setStudentPreferences(Map<String, List<String>> studentPreferences) {
        this.studentPreferences = studentPreferences;
    }

    public Map<String, List<String>> getCoursePreferences() {
        return coursePreferences;
    }

    public void setCoursePreferences(Map<String, List<String>> coursePreferences) {
        this.coursePreferences = coursePreferences;
    }

}