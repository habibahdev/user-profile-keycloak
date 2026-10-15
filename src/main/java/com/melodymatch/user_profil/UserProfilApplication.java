package com.melodymatch.user_profil;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class UserProfilApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfilApplication.class, args);
	}

}
