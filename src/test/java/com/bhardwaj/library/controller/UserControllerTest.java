package com.bhardwaj.library.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bhardwaj.library.model.UserCredentialsModel;
import com.bhardwaj.library.service.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserCredentialsModel credentials;

    @BeforeEach
    public void setUp() {
        credentials = new UserCredentialsModel("root", "root");
    }

    @Test
    public void testVerifyLoginCredentials() throws Exception {
        when(userService.verifyLoginCredentials(any(UserCredentialsModel.class))).thenReturn(true);

        mockMvc.perform(post("/verify-login-credentials")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"root\", \"password\": \"root\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }
}