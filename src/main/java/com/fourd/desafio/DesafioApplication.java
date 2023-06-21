package com.fourd.desafio;

import com.fourd.desafio.security.auth.domain.User;
import com.fourd.desafio.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository repository){
		return args -> {
			Optional<User> optional = repository.findUserByLogin("admin");
			if(optional.isEmpty()){
				var user = User.builder()
						.login("admin")
						.password("$2a$10$fkGrnu66fhdOl.TIMTnRJOCfYX3bzHJX6i7dEITbY.nkHZdfNlfPm")
						.status("APROVADO")
						.build();
				repository.insert(user);
			}
		};
	}

}
