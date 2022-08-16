package com.bridgelabz.notesapp.controller;

import com.bridgelabz.notesapp.dto.UserDTO;
import com.bridgelabz.notesapp.model.UserModel;
import com.bridgelabz.notesapp.service.IUserService;
import com.bridgelabz.notesapp.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/adduser")
    public UserModel addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/updateuser/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO, @RequestHeader String token) {
        return userService.updateUser(id, userDTO, token);
    }

    @GetMapping("/getalluser")
    public List<UserModel> getAllUser(@RequestHeader String token) {
        return userService.getAllUserDetails(token);
    }

    @DeleteMapping("/deleteuser/{id}")
    public UserModel deleteUser(@PathVariable Long id, @RequestHeader String token) {
        return userService.deleteUser(id, token);
    }

    @GetMapping("/getuserdetails/{id}")
    public UserModel getUserDetails(@PathVariable Long id, @RequestHeader String token) {
        return userService.getUserDetails(id, token);
    }

    @PostMapping("/login")
    public ResponseUtil userLogin(@RequestParam String emailId, @RequestParam String password) {
        return userService.userLogin(emailId, password);
    }

}
