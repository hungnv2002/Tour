package com.example.nvh_Website.service;

import com.example.nvh_Website.dto.BookingDTO;
import com.example.nvh_Website.dto.BookingDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    Page<BookingDTO> findAllBooking(Integer trang_thai, String ten_tour, Pageable pageable);
    List<BookingDTO> findBookingByUserId(Long user_Id);
    Page<BookingDTO> findBookingByTourId(Long tour_Id, Pageable pageable);
    boolean addNewBooking (BookingDTO newBooking);
    boolean approveBooking(Long bookingId, Integer trang_thai);
    boolean deleteBooking(Long id);
    BookingDTO getBookingById(Long id );
    BookingDetailDTO getBookingDetailById(Long id);

}
