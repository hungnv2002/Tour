package com.example.nvh_Website.repository;

import com.example.nvh_Website.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourGuideRepository extends JpaRepository<Tour, Long> {

}
