package com.example.nvh_Website.repository;

import com.example.nvh_Website.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {

}
