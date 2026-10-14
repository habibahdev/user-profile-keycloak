package com.melodymatch.user_profil;

import com.melodymatch.user_profil.model.Information;
import com.melodymatch.user_profil.model.Profil;
import com.melodymatch.user_profil.model.SocialMedia;
import com.melodymatch.user_profil.model.User;
import com.melodymatch.user_profil.model.enums.Gender;
import com.melodymatch.user_profil.model.enums.Orientation;
import com.melodymatch.user_profil.model.enums.Situation;
import com.melodymatch.user_profil.repository.*;
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
		Dotenv dotenv = Dotenv.configure().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(UserProfilApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataInitializer(
			final UserRepository userRepository,
			final ProfilRepository profilRepository,
			final InformationRepository informationRepository,
			final SocialMediaRepository socialMediaRepository,
			final UserSocialMediaRepository userSocialMediaRepository
	) {
		return args -> {
			List<SocialMedia> platforms = List.of(
					SocialMedia.builder().platformName("Spotify").build(),
					SocialMedia.builder().platformName("Deezer").build(),
					SocialMedia.builder().platformName("YouTube Music").build(),
					SocialMedia.builder().platformName("Apple Music").build(),
					SocialMedia.builder().platformName("Tidal").build()
			);
			socialMediaRepository.saveAll(platforms);

			for (int i = 1; i <= 5; i++) {
				String locale;
				switch (i % 5) {
					case 0: locale = "en"; break;
					case 1: locale = "en-US"; break;
					case 2: locale = "fr-FR"; break;
					case 3: locale = "es-ES"; break;
                    default: locale = "pt-BR"; break;
				}

				Double latitude = 40.0 + (i * 0.01);
				Double longitude = -74.0 + (i * 0.01);

				User user = User.builder()
						.firstName("Firstname" + i)
						.lastName("Lastname" + i)
						.pseudo("pseudo" + i)
						.birthDate(LocalDate.of(1990 + i, i, i))
						.email("user" + i +"@api.com")
						.phone("01234567" + i)
						.locale(locale)
						.latitude(latitude)
						.longitude(longitude)
						.locationUpdatedAt(LocalDateTime.now())
						.build();

				Profil profil = Profil.builder()
						.photo("profil" + i + ".png")
						.biography("user" + i + "'s bio")
						.user(user)
						.build();

				Information information = Information.builder()
						.age(30 + i)
						.gender(i % 2 == 0 ? Gender.MAN : Gender.WOMAN)
						.situation(i % 2 == 0 ? Situation.SINGLE : Situation.DIVORCED)
						.orientation(i % 2 == 0 ? Orientation.HETEROSEXUAL : Orientation.HOMOSEXUAL)
						.build();

				profil.setInformation(information);
				userRepository.save(user);
			}
		};
	}

}
