package com.carloan.loanprocessing.client;


import com.carloan.loanprocessing.model.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient("userprofile")
public interface UserProfileClient {

    @GetMapping("/api/v1/user/{userId}")
    Optional<UserProfile> findUserProfileById(@PathVariable("userId") int user_id);

    @PostMapping("/api/v1/user/checkLoanEligibility/{userId}")
    boolean getLoanEligibilityStatus(@PathVariable("userId") int user_id);
}
