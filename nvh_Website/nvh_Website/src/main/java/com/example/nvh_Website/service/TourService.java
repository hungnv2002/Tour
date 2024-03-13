package com.example.nvh_Website.service;

import com.example.nvh_Website.dto.TourDTO;
import com.example.nvh_Website.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface TourService {
    Page<TourDTO>findAllTour(String ten_tour, Long gia_tour_from, Long gia_tour_to,
                             Date ngay_khoi_hanh, Integer loai_tour, Pageable pageable);
    Page<TourDTO> findAllTourAdmin(String ten_tour,Long gia_tour_from,Long gia_tour_to,
                                   Date ngay_khoi_hanh,Integer loai_tour,Pageable pageable);
    TourDTO findTourById(Long id);
    boolean saveTour(Tour tour);
    Tour findFirstByOrderByIdDesc();
    Tour addTour(TourDTO tourDTO);
    Tour updateTour(TourDTO newTour, Long id);
    boolean deleteTour(Long id);
}
