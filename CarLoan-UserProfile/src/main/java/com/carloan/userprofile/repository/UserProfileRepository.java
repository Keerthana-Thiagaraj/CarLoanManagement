package com.carloan.userprofile.repository;

import com.carloan.userprofile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    List<UserProfile> findByEmail(String email);
}
