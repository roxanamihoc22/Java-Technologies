package com.example.PrefSchedule.repositories;

import com.example.PrefSchedule.entities.Instructor;
import org.springframework.data.jpa.repository.*;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Instructor findByEmail(String email);

}


