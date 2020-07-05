package com.fake.lukee.test.service;

import com.fake.lukee.test.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();

    User saveUser(User user) throws Exception;

    void deleteUser(String id) throws Exception;

    Optional<User> findByID(String id);
}

