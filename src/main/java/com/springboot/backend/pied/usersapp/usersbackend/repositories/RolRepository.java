package com.springboot.backend.pied.usersapp.usersbackend.repositories;

import com.springboot.backend.pied.usersapp.usersbackend.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
