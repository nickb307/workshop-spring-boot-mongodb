package com.user.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.workshopmongo.domain.User;
import com.user.workshopmongo.dto.UserDTO;
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
	
	public User insert(User obj){
		return repo.insert(obj);
	}
	
	public void delete (String id){
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		
		return repo.save(newObj);
		
	}
	
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
	
	
}
