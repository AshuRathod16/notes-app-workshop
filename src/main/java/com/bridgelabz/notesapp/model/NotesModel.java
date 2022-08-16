package com.bridgelabz.notesapp.model;

import com.bridgelabz.notesapp.dto.NotesDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "Notes")
@Data
public class NotesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String note;
    private String description;
    private String labels;
    private String user_id;
    private LocalDateTime registerDate;
    private LocalDateTime updatedDate;

    public NotesModel(NotesDTO noteDTO) {
        this.note = noteDTO.getNote();
        this.description = noteDTO.getDescription();
        this.labels = noteDTO.getLabels();
        this.user_id = noteDTO.getUser_id();
    }

    public NotesModel() {
    }
}
