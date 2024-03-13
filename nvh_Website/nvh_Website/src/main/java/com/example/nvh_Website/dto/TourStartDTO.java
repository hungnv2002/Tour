package com.example.nvh_Website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourStartDTO {
	private Long id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngay_khoi_hanh;
}
