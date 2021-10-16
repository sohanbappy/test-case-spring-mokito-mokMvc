package com.bappy.test;

import com.bappy.test.entity.TestResponse;
import com.bappy.test.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationForTestCaseApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addUserTest() throws Exception {
        User user = new User();
        user.setName("test-1");
        user.setPhone("01111");
        user.setEmail("test@yahoo.com");
        String request = mapper.writeValueAsString(user);
        MvcResult result = mockMvc.perform(post("/api/addUser").content(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        TestResponse response = mapper.readValue(resultContent, TestResponse.class);
        Assert.assertTrue(response.isStatus() == Boolean.TRUE);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/allUsers")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        TestResponse response = mapper.readValue(resultContent, TestResponse.class);
        Assert.assertTrue(response.isStatus() == Boolean.TRUE);
    }
}
