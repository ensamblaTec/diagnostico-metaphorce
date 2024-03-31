package com.spring.security.persistence.repositories;

import com.spring.security.persistence.entities.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NotesRepository extends JpaRepository<NotesEntity, Long> {
    @Query(value = "SELECT * FROM NOTES WHERE user_id = :id", nativeQuery = true)
    Optional<NotesEntity> findByUserId(Long user_id);
}
