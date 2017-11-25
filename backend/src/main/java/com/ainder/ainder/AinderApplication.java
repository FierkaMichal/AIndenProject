package com.ainder.ainder;

import com.ainder.ainder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
public class AinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AinderApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository) throws Exception {
		//Setup a default user if db is empty
//		if (repository.count()==0)
//			service.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		builder.userDetailsService(s -> new CustomUserDetails(repository.findByLogin(s)));
	}
}
