package com.spring.security.persistence.entities;

import jakarta.persistence.*;
import org.springframework.core.metrics.StartupStep;

import javax.swing.text.html.HTML;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="notes")
public class NotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date created_at;

    @ManyToMany
    @JoinTable(
            name = "notes_tags",
            joinColumns = @JoinColumn(name = "notes_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    private Set<TagsEntity> tags;
}
