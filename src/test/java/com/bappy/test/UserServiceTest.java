package com.bappy.test;

import com.bappy.test.entity.User;
import com.bappy.test.repository.UserRepo;
import com.bappy.test.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/*
To enable to @Mock annotation we have to add the annotation @ExtendWith(MockitoExtension.class)

 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    UserService userService;

    @BeforeEach
    public void initialize() {
        userService = new UserService(userRepo);
    }

    @Test
    @DisplayName("Get User By ID")
    void getUserByIdTest() throws Exception {
        User expectedUser = new User(500, "SB", "0166", "sb@gmail.com");
        //for Every dependency of UserService we need to Mock (for repositories)
        Mockito.when(userRepo.findById(500)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getUserById(500);
        Assertions.assertThat(actualUser.getId()).isEqualTo(expectedUser.getId());

        //Checking How may times Repo calls
        Mockito.verify(userRepo, Mockito.times(1)).findById(500);
    }

    @Test
    @DisplayName("Save New User")
    void saveUserTest() throws Exception {
        User expectedUser = new User(500, "SB", "0166", "sb@gmail.com");
        userService.saveUser(expectedUser);

        Mockito.verify(userRepo, Mockito.times(1)).save(userArgumentCaptor.capture());

        Assertions.assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(expectedUser.getId());
        Assertions.assertThat(userArgumentCaptor.getValue().getName()).isEqualTo(expectedUser.getName());
    }
}
