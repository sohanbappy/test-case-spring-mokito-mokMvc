package com.bappy.test;

import com.bappy.test.controller.WebController;
import com.bappy.test.entity.User;
import com.bappy.test.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

/*
@WithMockUser(username = "sohan", roles = {"USER", "ADMIN"}) - It will be applied to ALL APIs of that method

.with(SecurityMockMvcRequestPostProcessors.user("sohan").roles("ADMIN", "USER"))) - applied for Only that API call


 */
@WebMvcTest(controllers = WebController.class)
public class SecurityTest {

    @MockBean
    UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Return Index View (Protected)")
    public void getAllUsersTest() throws Exception {
        User user1 = new User(101, "sohan", "01766", "sohan@gmail.com");
        User user2 = new User(102, "bappy", "01866", "bappy@gmail.com");

        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));


        //Get token from another API call - permitAll()
        String token = "ejwfsbdfgdhgbfjbdfghkgj";//dummy


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/index")
                .with(SecurityMockMvcRequestPostProcessors.user("sohan").roles("ADMIN", "USER"))
                .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("msg"))
                .andExpect(MockMvcResultMatchers.model().attribute("type", "guest"))
                .andExpect(MockMvcResultMatchers.model().attribute("userList", Matchers.contains(user1, user2)))
                .andReturn();
    }
}
