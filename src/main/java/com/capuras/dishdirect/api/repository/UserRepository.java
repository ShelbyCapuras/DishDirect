package com.capuras.dishdirect.api.repository;
import com.capuras.dishdirect.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);
}
