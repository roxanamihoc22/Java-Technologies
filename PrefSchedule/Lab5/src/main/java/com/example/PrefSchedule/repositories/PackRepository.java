package com.example.PrefSchedule.repositories;

import com.example.PrefSchedule.entities.Pack;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findByYear(int year);

}

