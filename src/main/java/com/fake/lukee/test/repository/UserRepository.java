package com.fake.lukee.test.repository;

import com.fake.lukee.test.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsernameAndIdNot(String username, String id);

    Optional<User> findFirstByEmailAndIdNot(String email, String id);

    Optional<User> findFirstByProfileGamerTagAndIdNot(String gamerTag,String id);

}
