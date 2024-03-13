package com.example.nvh_Website.service.impl;

import com.example.nvh_Website.entity.Image;
import com.example.nvh_Website.repository.ImageRepository;
import com.example.nvh_Website.repository.TourRepository;
import com.example.nvh_Website.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    TourRepository tourRepository;
    @Override
    public List<Image> findByTourId(Long id) {
        return this.imageRepository.findByTourId(id);
    }

    @Override
    public Image addTour(long tourId, String url) {
        Image image= new Image();
        if(this.tourRepository.findTourById(tourId)!=null){
            image.setTour_id(tourId);
            image.setUrl(url);
            return  this.imageRepository.save(image);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        this.imageRepository.deleteById(id);

    }
}
