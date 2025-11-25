package com.springboot.backend.pied.usersapp.usersbackend.repositories;


import com.springboot.backend.pied.usersapp.usersbackend.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
