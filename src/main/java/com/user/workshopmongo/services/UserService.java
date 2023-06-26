package com.user.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.workshopmongo.domain.User;
import com.user.workshopmongo.repository.UserRepository;
import com.user.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		User user =repo.findById(id).orElseThrow(
	            () -> new ObjectNotFoundException("Usuário: " + id+ " não encontrado")
		        ); 
		return user;
	}
	
	
	
}
