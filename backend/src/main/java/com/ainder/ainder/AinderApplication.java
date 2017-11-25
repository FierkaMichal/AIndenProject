package com.ainder.ainder;

import com.ainder.ainder.config.CustomUserDetails;
import com.ainder.ainder.repositories.UserRepository;
import com.ainder.ainder.services.RoleServiceImpl;
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
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserServiceImpl userService, RoleServiceImpl roleService) throws Exception {
		//Setup a default user if db is empty
//		if (repository.count()==0)
//			service.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));

//		roleService.save(new Role("USER"));
//		userService.save(new User(1, "Michal","Fierka","mf", "123", "bla bla bla","zdjecie_url",5l,5l,roleService.getRoleById(2l)));

		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return login -> new CustomUserDetails(repository.findByLogin(login));
	}
}
