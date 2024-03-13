package com.example.nvh_Website.service;

import com.example.nvh_Website.entity.Image;

import java.util.List;

public interface ImageService {
    List<Image> findByTourId(Long id);
    public Image addTour(long tourId, String url);
    void deleteById(Long id);
}
