package com.springboot.backend.pied.usersapp.usersbackend.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest implements IUser {
    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    @NotBlank
    @Email
    private String email;

    private boolean admin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {}
}
