package com.bridgelabz.notesapp.service;

import com.bridgelabz.notesapp.dto.UserDTO;
import com.bridgelabz.notesapp.exception.UserException;
import com.bridgelabz.notesapp.model.UserModel;
import com.bridgelabz.notesapp.repository.UserRepository;
import com.bridgelabz.notesapp.util.ResponseUtil;
import com.bridgelabz.notesapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;


    @Override
    public UserModel addUser(UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO);
        userModel.setRegisterDate(LocalDateTime.now());
        userRepository.save(userModel);
        String body = "User is added successfully with user id is :" + userModel.getId();
        String subject = "User added Successfully....";
        mailService.send(userModel.getEmailId(), body, subject);
        return userModel;
    }

    @Override
    public UserModel updateUser(Long id, UserDTO userDTO, String token) {
        Long contactId = tokenUtil.decodeToken(token);
        Optional<UserModel> isContact = userRepository.findById(contactId);
        if (isContact.isPresent()) {
            Optional<UserModel> isContactPresent = userRepository.findById(id);
            if (isContactPresent.isPresent()) {
                isContactPresent.get().setFirstName(userDTO.getFirstName());
                isContactPresent.get().setLastName(userDTO.getLastName());
                isContactPresent.get().setEmailId(userDTO.getEmailId());
                isContactPresent.get().setPassword(userDTO.getPassword());
                isContactPresent.get().setUpdatedDate(LocalDateTime.now());
                userRepository.save(isContactPresent.get());
                String body = "User Details Updated Successfully with User id is :" + isContactPresent.get().getId();
                String subject = "User Details Updated Successfully....";
                mailService.send(isContactPresent.get().getEmailId(), body, subject);
                return isContactPresent.get();
            } else {
                throw new UserException(400, "User is not Found");
            }
        }
        throw new UserException(400, "Invalid Token");
    }

    @Override
    public List<UserModel> getAllUserDetails(String token) {
        Long contactId = tokenUtil.decodeToken(token);
        Optional<UserModel> isContactIsPresent = userRepository.findById(contactId);
        if (isContactIsPresent.isPresent()) {
            List<UserModel> isContactPresent = userRepository.findAll();
            if (isContactPresent.size() > 0) {
                return isContactPresent;
            } else {
                throw new UserException(400, "No User Is there");
            }
        }
        throw new UserException(400, "Invalid Token");
    }

    @Override
    public UserModel deleteUser(Long id, String token) {
        Long contactId = tokenUtil.decodeToken(token);
        Optional<UserModel> isContact = userRepository.findById(contactId);
        if (isContact.isPresent()) {
            Optional<UserModel> isContactPresent = userRepository.findById(id);
            if (isContactPresent.isPresent()) {
                userRepository.delete(isContactPresent.get());
                String body = "User Deleted Successfully with User id is :" + isContactPresent.get().getId();
                String subject = "User Deleted Successfully....";
                mailService.send(isContactPresent.get().getEmailId(), body, subject);
                return isContactPresent.get();
            } else {
                throw new UserException(400, "User not Found");
            }
        }
        throw new UserException(400, "Invalid Token");
    }

    @Override
    public UserModel getUserDetails(Long id, String token) {
        Long contactId = tokenUtil.decodeToken(token);
        Optional<UserModel> isContact = userRepository.findById(contactId);
        if (isContact.isPresent()) {
            Optional<UserModel> isContactPresent = userRepository.findById(id);
            if (isContactPresent.isPresent()) {
                return isContactPresent.get();
            } else {
                throw new UserException(400, "User not found");
            }
        }
        throw new UserException(400, "Invalid Token");
    }

    @Override
    public ResponseUtil userLogin(String emailId, String password) {
        Optional<UserModel> isEmailPresent = userRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new ResponseUtil(200, "Login Successful", token);
            } else {
                throw new UserException(400, "Password is wrong");
            }
        }
        throw new UserException(400, "No User Is Found");
    }
}
