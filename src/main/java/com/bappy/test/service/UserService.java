package com.bappy.test.service;

import com.bappy.test.entity.User;
import com.bappy.test.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User getUserById(int id) throws Exception {
        return userRepo.findById(id)
                .orElseThrow(() -> new Exception("User Not Found!"));
    }
}
