package com.user.workshopmongo.services;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.workshopmongo.domain.Post;
import com.user.workshopmongo.repository.PostRepository;
import com.user.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	
	public Post findById(String id) {
		Post post =repo.findById(id).orElseThrow(
	            () -> new ObjectNotFoundException("Post: " + id+ " não encontrado")
		        ); 
		return post;
	}
	
	public List<Post> findByTitle(String text){
		return repo.findTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 *60* 60*1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
}
