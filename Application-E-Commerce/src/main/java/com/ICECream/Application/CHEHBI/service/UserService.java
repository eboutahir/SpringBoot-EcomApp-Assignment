package com.ICECream.Application.CHEHBI.service;


import java.util.List;

import com.ICECream.Application.CHEHBI.domain.User;

public interface UserService {
	
	User findById(Long id);
	
	User findByUsername(String username);
		
	User findByEmail(String email);
		
	void save(User user);
	
	User createUser(String username, String email,  String password, List<String> roles);

}
