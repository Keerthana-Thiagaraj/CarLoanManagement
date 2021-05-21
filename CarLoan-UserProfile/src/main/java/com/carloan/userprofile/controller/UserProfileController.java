package com.carloan.userprofile.controller;

import com.carloan.userprofile.dto.UserProfileDTO;
import com.carloan.userprofile.exception.UserProfileAlreadyExistsException;
import com.carloan.userprofile.exception.UserProfileNotFoundException;
import com.carloan.userprofile.model.UserProfile;
import com.carloan.userprofile.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/")
    public ResponseEntity<String> registerUserProfile(@RequestBody @Valid UserProfileDTO userProfileDTO) {

        ResponseEntity<String> entity;
        try {
            UserProfile userProfile = new UserProfile(userProfileDTO.getName(), userProfileDTO.getEmail(),
                    userProfileDTO.getSalary(), userProfileDTO.getContact());
            userProfileService.registerUser(userProfile);
            entity = new ResponseEntity(userProfile, HttpStatus.CREATED);
        } catch (UserProfileAlreadyExistsException e) {
            entity = new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
        }
        return entity;
    }

    @PutMapping("/updateUser/{email}")
    public ResponseEntity<String> updateUserProfile(@PathVariable("email") String email, @RequestBody UserProfileDTO userProfileDTO) {

        ResponseEntity<String> entity;
        try {
            UserProfile userProfile = new UserProfile(userProfileDTO.getName(), userProfileDTO.getEmail(),
                    userProfileDTO.getSalary(), userProfileDTO.getContact());

            userProfileService.updateUserDetails(email, userProfile);
            entity = new ResponseEntity("User details updated", HttpStatus.OK);
        } catch (UserProfileNotFoundException e) {
            entity = new ResponseEntity<String>("User doesn't exist", HttpStatus.CONFLICT);
        }
        return entity;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserProfile>> fetchUserProfiles() {

        return ResponseEntity.status(HttpStatus.OK).body(userProfileService.getAllUsers());
    }

}
