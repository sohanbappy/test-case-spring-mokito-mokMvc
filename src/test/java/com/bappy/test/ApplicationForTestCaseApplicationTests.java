package com.bappy.test;

import com.bappy.test.controller.HelloController;
import com.bappy.test.entity.TestResponse;
import com.bappy.test.entity.User;

import com.bappy.test.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = HelloController.class)
public class ApplicationForTestCaseApplicationTests {

    @MockBean
    UserService userService;
    @Autowired
    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Save User")
    public void addUserTest() throws Exception {
        User user = new User();
        user.setName("test-1");
        user.setPhone("01111");
        user.setEmail("test@yahoo.com");
        String request = mapper.writeValueAsString(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/addUser").content(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();

    }

    @Test
    @DisplayName("All User List")
    public void getAllUsersTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/allUsers")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        TestResponse response = mapper.readValue(resultContent, TestResponse.class);
    }
}
