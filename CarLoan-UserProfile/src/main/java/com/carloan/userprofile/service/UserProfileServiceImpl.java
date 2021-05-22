package com.carloan.userprofile.service;

import com.carloan.userprofile.exception.UserProfileAlreadyExistsException;
import com.carloan.userprofile.exception.UserProfileNotFoundException;
import com.carloan.userprofile.model.UserProfile;
import com.carloan.userprofile.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            if (userProfileRepository.findByEmail(userProfile.getEmail()).isPresent()) {
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

    @Override
    public UserProfile updateUserDetails(String email, UserProfile userProfile) throws UserProfileNotFoundException {
        UserProfile user = userProfileRepository.findByEmail(email).orElseThrow(() -> new UserProfileNotFoundException("User doesn't exist"));

        try {
            user.setEmail(userProfile.getEmail());
            user.setContact(userProfile.getContact());
            user.setName(userProfile.getName());
            user.setSalary(userProfile.getSalary());
            userProfileRepository.save(user);
        } catch (Exception e) {
            throw new UserProfileNotFoundException("User doesn't exist");
        }
        return user;
    }

    @Override
    public boolean findLoanEligibility(int user_id) throws UserProfileNotFoundException {
        UserProfile userProfile = userProfileRepository.findByUserId(user_id).orElseThrow(() -> new UserProfileNotFoundException("User doesn't exist"));
        if (userProfile.getSalary() > 50000) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<UserProfile> findUserProfileById(int user_id) {
        return userProfileRepository.findByUserId(user_id);
    }
}
