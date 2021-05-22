package com.carloan.loanprocessing.controller;

import com.carloan.loanprocessing.client.UserProfileClient;
import com.carloan.loanprocessing.dto.LoanRequestDto;
import com.carloan.loanprocessing.repository.LoanRepository;
import com.carloan.loanprocessing.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@ExtendWith(MockitoExtension.class)
public class LoanProcessingControllerTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Mock
    LoanService loanService;

    @Mock
    LoanRepository loanRepository;

    @Mock
    UserProfileClient userProfileClient;

    @InjectMocks
    LoanController loanController;

    @Autowired
    private MockMvc mockMvc;

    private LoanRequestDto loanRequestDto;

    @Test
    public void testApplyLoanSuccess() throws Exception {

        loanRequestDto = new LoanRequestDto(1, 50000.0);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api/v1/loan/apply").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(loanRequestDto));
        mockMvc.perform(builder).andExpect(status().isCreated());
    }

    @Test
    public void testApplyLoanFailure() throws Exception {
        loanRequestDto = new LoanRequestDto(1, 100.0);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api/v1/loan/apply").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(loanRequestDto));
        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }
}
