package com.carloan.userprofile.controller;


import com.carloan.userprofile.dto.UserProfileDTO;
import com.carloan.userprofile.model.UserProfile;
import com.carloan.userprofile.repository.UserProfileRepository;
import com.carloan.userprofile.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserProfileControllerTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Mock
    UserProfileService userProfileService;

    @Mock
    UserProfileRepository userProfileRepository;

    @InjectMocks
    UserProfileController userProfileController;

    private UserProfileDTO userProfile;

    private UserProfile userProfileStubData = new UserProfile(1, "keerthana", "keertana.t1308@gmail.com", 200000.0, 9500387199L);

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
        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateUserProfileSuccess() throws Exception {

        Mockito.when(userProfileService.updateUserDetails(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(userProfileStubData);
        mockMvc.perform(put("/api/v1/user/" + userProfileStubData.getEmail())
                .content(mapper.writeValueAsString(userProfileStubData))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserProfileFailure() throws Exception {

        Mockito.when(userProfileService.updateUserDetails(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(userProfileStubData);
        mockMvc.perform(put("/api/v1/user/" + "keee")
                .content(mapper.writeValueAsString(userProfileStubData))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

}
