package com.example.PrefSchedule.dto;

public class InstructorPreferenceRequestDTO {

    private String compulsoryAbbr;
    private double percentage;

    public String getCompulsoryAbbr() {
        return compulsoryAbbr;
    }

    public void setCompulsoryAbbr(String compulsoryAbbr) {
        this.compulsoryAbbr = compulsoryAbbr;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
