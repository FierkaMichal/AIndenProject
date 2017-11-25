package com.ainder.ainder;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.entities.Role;
import com.ainder.ainder.entities.User;
import com.ainder.ainder.repositories.UserRepository;
import com.ainder.ainder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AinderApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AinderApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserServiceImpl userService) throws Exception {
		//Setup a default user if db is empty
//		if (repository.count()==0)
//			service.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));

		userService.save(new User(6, "aa","aa","pass", "passwo", "elo","ssss",5l,5l,new Role("USER")));

		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return login -> new CustomUserDetails(repository.findByLogin(login));
	}
}
