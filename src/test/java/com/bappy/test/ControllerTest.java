package com.bappy.test;

import com.bappy.test.controller.HelloController;
import com.bappy.test.entity.User;

import com.bappy.test.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;


@WebMvcTest(controllers = HelloController.class)
public class ControllerTest {

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

        Mockito.when(userService.saveUser(user)).thenReturn(user);

        String request = mapper.writeValueAsString(user);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/addUser").content(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test-1")))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    @Test
    @DisplayName("All User List")
    public void getAllUsersTest() throws Exception {
        User user1 = new User(101, "sohan", "01766", "sohan@gmail.com");
        User user2 = new User(102, "bappy", "01866", "bappy@gmail.com");

        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/allUsers")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(101)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("sohan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("bappy@gmail.com")))
                .andReturn();
    }
}
