package com.example.nvh_Website.repository;

import com.example.nvh_Website.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

	@Query(value="select i from Image i where i.tour_id = :id")
	List<Image> findByTourId(@Param("id") Long id);

}
