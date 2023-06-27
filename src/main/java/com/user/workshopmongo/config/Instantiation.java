package com.user.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.user.workshopmongo.domain.Post;
import com.user.workshopmongo.domain.User;
import com.user.workshopmongo.dto.AuthorDTO;
import com.user.workshopmongo.repository.PostRepository;
import com.user.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		postRepository.deleteAll();;
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		
		userRepository.save(maria); //primeiro salva usuario para terem um id proprio criado pelo bd depois salva os post copiando os dados do autor
		userRepository.save(bob);
		userRepository.save(alex);
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, abraços", new  AuthorDTO (maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new  AuthorDTO (maria));
		
		postRepository.save(post1);
		postRepository.save(post2);
		
		maria.getPosts().add(post1);
		maria.getPosts().add(post2);
		userRepository.save(maria);
		
		
	}

}
