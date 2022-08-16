package com.bridgelabz.notesapp.service;

import com.bridgelabz.notesapp.dto.NotesDTO;
import com.bridgelabz.notesapp.exception.UserException;
import com.bridgelabz.notesapp.model.NotesModel;
import com.bridgelabz.notesapp.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService implements INotesService {

  @Autowired
  NotesRepository noteRepository;


    @Override
    public NotesModel addNotes(NotesDTO noteDTO){
       NotesModel noteModel = new NotesModel(noteDTO);
        noteModel.setRegisterDate(LocalDateTime.now());
        noteRepository.save(noteModel);
        return noteModel;
    }

    @Override
    public NotesModel updateNotes(Long id, NotesDTO noteDTO){
        Optional<NotesModel> isContactPresent = noteRepository.findById(id);
        if (isContactPresent.isPresent()){
            isContactPresent.get().setNote(noteDTO.getNote());
            isContactPresent.get().setDescription(noteDTO.getDescription());
            isContactPresent.get().setLabels(noteDTO.getLabels());
            isContactPresent.get().setUser_id(noteDTO.getUser_id());
            isContactPresent.get().setUpdatedDate(LocalDateTime.now());
            noteRepository.save(isContactPresent.get());
            return isContactPresent.get();
        } else {
            throw new UserException(400, "Contact is not Found");
        }
    }

    @Override
    public List<NotesModel> getAllNotes() {
        List<NotesModel> isContactPresent = noteRepository.findAll();
        if (isContactPresent.size() > 0){
            return isContactPresent;
        } else {
            throw new UserException(400, "No Contact Is There");
        }
    }

    @Override
    public NotesModel deleteNotes(Long id){
        Optional<NotesModel> isContactPresent = noteRepository.findById(id);
        if (isContactPresent.isPresent()){
            noteRepository.delete(isContactPresent.get());
            return isContactPresent.get();
        } else {
            throw new UserException(400, "Contact Not Found");
        }
    }

    @Override
    public NotesModel getNotes(Long id){
        Optional<NotesModel> isContactPresent = noteRepository.findById(id);
        if (isContactPresent.isPresent()){
            return isContactPresent.get();
        } else {
            throw new UserException(400, "Contact Not Found");
        }
    }
}
