package com.example.StableMatch.dto;

import java.util.List;

public class StableMatchResponseDTO {

    private List<AssignmentDTO> assignments;

    public StableMatchResponseDTO() {}

    public StableMatchResponseDTO(List<AssignmentDTO> assignments) {
        this.assignments = assignments;
    }

    public List<AssignmentDTO> getAssignments() { return assignments; }
    public void setAssignments(List<AssignmentDTO> assignments) {
        this.assignments = assignments;
    }
}
