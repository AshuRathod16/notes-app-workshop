package com.bridgelabz.notesapp.repository;

import com.bridgelabz.notesapp.model.NotesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<NotesModel, Long> {
}
