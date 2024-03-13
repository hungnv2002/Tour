package com.example.nvh_Website.utilities;

import  com.example.nvh_Website.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtilities {

	public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attr.getRequest().getSession();
    }
	
	public static String getUsername() {
		return (String) getSession().getAttribute("username");
	}
	
	public static void setUsername(String username) {
		getSession().setAttribute("username", username);
	}
	
	public static void setUser(UserDTO user) {
		getSession().setAttribute("user", user);
	}
	
	public static UserDTO getUser() {
		return (UserDTO) getSession().getAttribute("user");
	}

	public static void setAdmin(UserDTO user) {
		getSession().setAttribute("admin",user);
	}
	public static UserDTO getAdmin() {
		return (UserDTO)getSession().getAttribute("admin");
	}

}
