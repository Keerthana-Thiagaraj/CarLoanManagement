package com.carloan.userprofile.controller;


import com.carloan.userprofile.dto.UserProfileDTO;
import com.carloan.userprofile.model.UserProfile;
import com.carloan.userprofile.service.UserProfileService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserProfileControllerTest {

    @Mock
    UserProfileService userProfileService;

    @InjectMocks
    UserProfileController userProfileController;

    private UserProfileDTO userProfile;

    @Test
    public void testSaveUserProfileSuccess() throws Exception {
        userProfile = new UserProfileDTO("keerthana", 20000.0, "keerthana.t12@gmail.com", 95000765L);
        userProfileController.registerUserProfile(userProfile);
        final ArgumentCaptor<UserProfile> argumentCaptor = ArgumentCaptor.forClass(UserProfile.class);
        Mockito.verify(userProfileService).registerUser(argumentCaptor.capture());
        Assertions.assertEquals("keerthana", argumentCaptor.getValue().getName());
    }
}
