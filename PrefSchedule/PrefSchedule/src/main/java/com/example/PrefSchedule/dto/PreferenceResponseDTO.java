package com.example.PrefSchedule.dto;


public class PreferenceResponseDTO {

    private Long id;
    private String studentName;
    private String courseName;
    private String packName;
    private int rank;

    public PreferenceResponseDTO(Long id, String studentName, String courseName, String packName, int rank) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.packName = packName;
        this.rank = rank;
    }

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getCourseName() { return courseName; }
    public String getPackName() { return packName; }
    public int getRank() { return rank; }
}

