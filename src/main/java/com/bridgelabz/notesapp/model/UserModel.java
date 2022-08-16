package com.bridgelabz.notesapp.model;

import com.bridgelabz.notesapp.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notes")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private LocalDateTime registerDate;
    private LocalDateTime updatedDate;

    public UserModel(UserDTO notesAppDTO) {
        this.firstName = notesAppDTO.getFirstName();
        this.lastName = notesAppDTO.getLastName();
        this.emailId = notesAppDTO.getEmailId();
        this.password = notesAppDTO.getPassword();
    }

    public UserModel() {
    }
}
