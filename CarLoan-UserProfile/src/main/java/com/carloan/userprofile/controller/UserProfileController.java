package com.carloan.userprofile.controller;

import com.carloan.userprofile.dto.UserProfileDTO;
import com.carloan.userprofile.exception.UserProfileAlreadyExistsException;
import com.carloan.userprofile.model.UserProfile;
import com.carloan.userprofile.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/user")
    public ResponseEntity<String> registerUserProfile(@RequestBody UserProfileDTO userProfileDTO) {

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
}