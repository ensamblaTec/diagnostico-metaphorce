package com.spring.security.persistence.repositories;

import com.spring.security.persistence.entities.NotesTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesTagsRepository extends JpaRepository<NotesTagsEntity, Long> {
}
