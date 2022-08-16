package com.bridgelabz.notesapp.service;

import com.bridgelabz.notesapp.dto.NotesDTO;
import com.bridgelabz.notesapp.model.NotesModel;

import java.util.List;

public interface INotesService {
    NotesModel addNotes(NotesDTO noteDTO);

    NotesModel updateNotes(Long id, NotesDTO noteDTO);

    List<NotesModel> getAllNotes();

    NotesModel deleteNotes(Long id);

    NotesModel getNotes(Long id);


}
