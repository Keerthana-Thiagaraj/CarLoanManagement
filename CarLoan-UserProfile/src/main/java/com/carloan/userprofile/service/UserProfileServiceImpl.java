package com.carloan.userprofile.service;

import com.carloan.userprofile.exception.UserProfileAlreadyExistsException;
import com.carloan.userprofile.model.UserProfile;
import com.carloan.userprofile.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile registerUser(UserProfile userProfile) throws UserProfileAlreadyExistsException {

        try {
            if (userProfileRepository.findByEmail(userProfile.getEmail()).size() > 0) {
                throw new UserProfileAlreadyExistsException("Error");
            } else if (userProfileRepository.save(userProfile) == null) {
                throw new UserProfileAlreadyExistsException("Error");
            } else {
                return userProfile;
            }
        } catch (Exception e) {
            throw new UserProfileAlreadyExistsException("Error");
        }
    }
}
