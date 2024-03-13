package com.example.nvh_Website.utilities;

import com.example.nvh_Website.dto.UserDTO;
import com.example.nvh_Website.entity.User;

public class ConvertUserToDto {
	
	public static UserDTO convertUsertoDto(User user) {
		return new UserDTO(user.getId(),user.getUsername(), user.getHo_ten(), user.getSdt(), user.getGioi_tinh(), user.getEmail(),
				user.getDia_chi(), user.getRole());
	}
	
}
