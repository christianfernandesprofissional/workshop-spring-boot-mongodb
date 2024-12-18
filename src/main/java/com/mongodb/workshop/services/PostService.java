package com.mongodb.workshop.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.workshop.domain.Post;
import com.mongodb.workshop.repository.PostRepository;
import com.mongodb.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado") );
	}
	
	
	public List<Post> findByTitle(String text){
		//return repo.findByTitleContainingIgnoreCase(text);
		return repo.searchTitle(text);
	}
	
	public List<Post> filteredSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.filteredSearch(text, minDate, maxDate);
	}
}
