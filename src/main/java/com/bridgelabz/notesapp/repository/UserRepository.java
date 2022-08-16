package com.bridgelabz.notesapp.repository;

import com.bridgelabz.notesapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmailId(String emailId);
}
