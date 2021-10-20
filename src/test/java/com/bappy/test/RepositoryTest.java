package com.bappy.test;

import com.bappy.test.entity.User;
import com.bappy.test.repository.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

/*
As the userId field is auto-incremented, we have to ignore that field from the comparison
Using H2 Database for test purpose, TestContainer can also be used for Real-DB
 */
@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {


    @Autowired
    UserRepo userRepo;
    User user;
    User savedUser;


    @BeforeEach
    public void initialize() {
        user = new User(500, "SB", "0166", "sb@gmail.com");
        savedUser = userRepo.save(user);
    }


    @Test
    @DisplayName("Save a User")
    public void saveUser() {
        Assertions.assertThat(savedUser).usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void saveUserUsingSqlFile() {
        Optional<User> testUser = userRepo.findById(801);
        Assertions.assertThat(testUser).isNotEmpty();
    }
}
