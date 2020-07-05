package com.fake.lukee.test.service.impl;

import com.fake.lukee.test.model.User;
import com.fake.lukee.test.repository.UserRepository;
import com.fake.lukee.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User saveUser(User user) throws Exception {

        if (userRepository.findByUsernameAndIdNot(user.getUsername(), user.getId()).isPresent()) {
            throw new Exception("Já existe um usuário com este username");
        }

        if (userRepository.findFirstByEmailAndIdNot(user.getEmail(), user.getId()).isPresent()) {
            throw new Exception("Já existe um usuário com este e-mail");
        }

        if (userRepository.findFirstByProfileGamerTagAndIdNot(user.getProfile().getGamerTag(), user.getId()).isPresent()) {
            throw new Exception("Já existe um usuário com esta GamerTag");
        }
        if (user.getId() != null) {
            Optional<User> serverUser = userRepository.findById(user.getId());
            serverUser.ifPresent(value -> user.setPassword(value.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return;
        }
        throw new Exception("Não foi possível excluir o usuário");
    }

    @Override
    public Optional<User> findByID(String id) {
        return userRepository.findById(id);
    }

}