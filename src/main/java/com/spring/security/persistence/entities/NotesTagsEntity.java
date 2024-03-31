package com.spring.security.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "notes_tags")
public class NotesTagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notes_id")
    private NotesEntity note;

    @ManyToOne
    @JoinColumn(name = "tags_id")
    private TagsEntity tag;

    private LocalDate added_at;
}
