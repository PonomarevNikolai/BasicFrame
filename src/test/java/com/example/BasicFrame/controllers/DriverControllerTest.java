package com.example.BasicFrame.controllers;
import com.example.BasicFrame.services.DriverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class DriverControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    DriverService driverService;




    @Test
    void getDrivers() throws Exception {


    }

    @Test
    void GetDriver() {
    }

    @Test
    void addDriver() {
    }

    @Test
    void updateDriver() {
    }

    @Test
    void deleteDriver() {
    }

    @Test
    void addRoleToDriver() {
    }

    @Test
    void addRole() {
    }

    @Test
    void getRole() {
    }
}