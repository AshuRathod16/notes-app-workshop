package com.bridgelabz.notesapp.service;

import com.bridgelabz.notesapp.dto.UserDTO;
import com.bridgelabz.notesapp.model.UserModel;
import com.bridgelabz.notesapp.util.ResponseUtil;

import java.util.List;

public interface IUserService {
    UserModel addUser(UserDTO userDTO);

    UserModel updateUser(Long id, UserDTO userDTO, String token);

    List<UserModel> getAllUserDetails(String token);

    UserModel deleteUser(Long id, String token);

    UserModel getUserDetails(Long id, String token);

    ResponseUtil userLogin(String emailId, String password);
}
