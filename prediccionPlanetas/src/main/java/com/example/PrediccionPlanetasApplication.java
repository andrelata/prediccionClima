package com.example;

import com.example.model.Planeta;
import com.example.repositories.PrediccionRepo;
import com.example.service.PrediccionClimaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import com.github.blocoio.faker;

@SpringBootApplication
public class PrediccionPlanetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrediccionPlanetasApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDb(PrediccionRepo repository){
		return (args) -> {
			Planeta ferengi = new Planeta(1, 1, 500);
			Planeta betasoide = new Planeta(3, 1, 2000);;
			Planeta vulcano = new Planeta(5, -1, 1000);;
			new PrediccionClimaService(ferengi, betasoide, vulcano, repository);
		};
	}
}
