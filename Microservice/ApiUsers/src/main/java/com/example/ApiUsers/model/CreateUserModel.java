package com.example.ApiUsers.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserModel {
    @NotNull(message="firstName cannot be null ")
    @Size(min=2,message = "firstName cannot be less then 2 characters")
    private String firstName;
    @NotNull(message="lastName cannot be null ")
    @Size(min=2,message = "lastName cannot be less then 2 characters")
    private String lastName;
    @NotNull(message="email cannot be null ")
    @Email // email format
    private String email;
    @NotNull(message="password cannot be null ")
    @Size(min=8,max=16,message = "password must be > 8 and <25 characters")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
