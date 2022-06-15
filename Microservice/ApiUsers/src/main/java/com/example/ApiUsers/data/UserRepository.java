package com.example.ApiUsers.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);//select from database
}
