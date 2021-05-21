package com.carloan.userprofile.repository;

import com.carloan.userprofile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    Optional<UserProfile> findByEmail(String email);

    Optional<UserProfile> findByUserId(int user_id);
}
