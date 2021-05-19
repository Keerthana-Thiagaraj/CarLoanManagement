package com.carloan.userprofile.service;

import com.carloan.userprofile.exception.UserProfileAlreadyExistsException;
import com.carloan.userprofile.exception.UserProfileNotFoundException;
import com.carloan.userprofile.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> getAllUsers();

    UserProfile registerUser(UserProfile userProfile) throws UserProfileAlreadyExistsException;

    void updateUserDetails(String email,UserProfile userProfile) throws UserProfileNotFoundException;
}
