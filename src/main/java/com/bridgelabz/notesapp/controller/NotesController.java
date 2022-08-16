package com.bridgelabz.notesapp.controller;

import com.bridgelabz.notesapp.dto.NotesDTO;
import com.bridgelabz.notesapp.model.NotesModel;
import com.bridgelabz.notesapp.service.INotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")

public class NotesController {

    @Autowired
    INotesService noteService;

    @PostMapping("/addnotes")
    public NotesModel addContact(@RequestBody NotesDTO noteDTO) {
        return noteService.addNotes(noteDTO);
    }

    @PutMapping("/updatenotes/{id}")
    public NotesModel updateNotes(@PathVariable Long id, @RequestBody NotesDTO noteDTO) {
        return noteService.updateNotes(id, noteDTO);
    }

    @GetMapping("/getallnotes")
    public List<NotesModel> getAllNotes() {
        return noteService.getAllNotes();
    }

    @DeleteMapping("/deletenote/{id}")
    public NotesModel deleteNote(@PathVariable Long id) {
        return noteService.deleteNotes(id);
    }

    @GetMapping("/getnotes/{id}")
    public NotesModel getNote(@PathVariable Long id) {
        return noteService.getNotes(id);
    }
}
