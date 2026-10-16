package com.melodymatch.user_profil.repository;

import com.melodymatch.user_profil.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {
}
