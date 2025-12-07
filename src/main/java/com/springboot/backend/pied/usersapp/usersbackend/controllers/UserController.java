package com.springboot.backend.pied.usersapp.usersbackend.controllers;

import com.springboot.backend.pied.usersapp.usersbackend.entities.User;
import com.springboot.backend.pied.usersapp.usersbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list(){
         return this.userService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<User> listPageable(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page,4);
        return this.userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id){
        Optional<User> userOptional = this.userService.findById(id);
        if(userOptional.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "User not found by Id: " + id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user,BindingResult result, @PathVariable Long id){

        if(result.hasErrors()){
            return validation(result);
        }

        Optional<User> userOptional = this.userService.findById(id);
        if(userOptional.isPresent()){
            User userDB = userOptional.get();
            userDB.setEmail(user.getEmail());
            userDB.setLastname(user.getLastname());
            userDB.setUsername(user.getUsername());
            userDB.setName(user.getName());
            userDB.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(this.userService.save(userDB));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<User> userOptional = this.userService.findById(id);
        if(userOptional.isPresent()){
            this.userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "The field " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
