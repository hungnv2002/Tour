package com.example.nvh_Website.service;

import com.example.nvh_Website.entity.TinTuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TinTucService {
    public Page<TinTuc> getAllPage(Pageable pageable);
    public TinTuc findOnePAge(Long id);
    public  void deleteOnePage(Long id);
    public  TinTuc createOnePage(TinTuc newTinTuc);
    public TinTuc updateTinTuc(TinTuc updateTinTuc, Long id);

}
