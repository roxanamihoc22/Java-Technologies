package com.example.PrefSchedule.repositories;

import com.example.PrefSchedule.entities.Pack;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findByYear(int year);

}

