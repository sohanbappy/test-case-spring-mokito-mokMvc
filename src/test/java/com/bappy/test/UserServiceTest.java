package com.bappy.test;

import com.bappy.test.entity.User;
import com.bappy.test.repository.UserRepo;
import com.bappy.test.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    UserRepo userRepo;

    @Test
    @DisplayName("Get User By ID")
    void getUserByIdTest() throws Exception {
        User expectedUser = new User(500, "SB", "0166", "sb@gmail.com");
        UserService service = new UserService(userRepo);
        //for Every dependency of UserService we need to Mock (for repositories)
        Mockito.when(userRepo.findById(500)).thenReturn(Optional.of(expectedUser));

        User actualUser = service.getUserById(500);
        Assertions.assertThat(actualUser.getId()).isEqualTo(expectedUser.getId());

        //Checking How may times Repo calls
        Mockito.verify(userRepo, Mockito.times(1)).findById(500);
    }
}
