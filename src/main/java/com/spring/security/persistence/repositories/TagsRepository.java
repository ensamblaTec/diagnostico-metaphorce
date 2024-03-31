package com.spring.security.persistence.repositories;

import com.spring.security.persistence.entities.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<TagsEntity, Long> {
}
