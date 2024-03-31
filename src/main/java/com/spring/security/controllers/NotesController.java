package com.spring.security.controllers;

import com.spring.security.services.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    INotesService notesService;

    @GetMapping("/{id}")
}
