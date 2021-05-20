package com.carloan.userprofile.controller;


import com.carloan.userprofile.dto.UserProfileDTO;
import com.carloan.userprofile.model.UserProfile;
import com.carloan.userprofile.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserProfileControllerTest {

    @Mock
    UserProfileService userProfileService;

    @InjectMocks
    UserProfileController userProfileController;

    private UserProfileDTO userProfile;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveUserProfileSuccess() throws Exception {
        userProfile = new UserProfileDTO("keerthana", 20000.0, "keerthana.t12@gmail.com", 95000765L);
        userProfileController.registerUserProfile(userProfile);
        final ArgumentCaptor<UserProfile> argumentCaptor = ArgumentCaptor.forClass(UserProfile.class);
        Mockito.verify(userProfileService).registerUser(argumentCaptor.capture());
        Assertions.assertEquals("keerthana", argumentCaptor.getValue().getName());
    }

    @Test
    public void testSaveUserProfileFailure() throws Exception {

        String invalidUserProfileRequest = "{    \"name\":\"\",\n" +
                "    \"salary\":\"1000091\",\n" +
                "    \"email\":\"kertana.t1308@com\",\n" +
                "    \"contact\":9500387198}";
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api/v1/user").contentType(MediaType.APPLICATION_JSON).content(invalidUserProfileRequest);
        mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
