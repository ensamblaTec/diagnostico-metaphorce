package com.spring.security.services;

import com.spring.security.persistence.entities.NotesEntity;

import java.util.List;

public interface INotesService {
    public List<NotesEntity> findAllNotesByUser();
}
