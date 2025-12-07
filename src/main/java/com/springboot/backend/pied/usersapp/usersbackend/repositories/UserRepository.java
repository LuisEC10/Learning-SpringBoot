package com.springboot.backend.pied.usersapp.usersbackend.repositories;


import com.springboot.backend.pied.usersapp.usersbackend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable pageable); // Devuelve una página con una cierta cantidad de datos
}
