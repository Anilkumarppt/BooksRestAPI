package com.booksapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private int id;
    private String username;
    @JsonIgnore
    private String password;
    private String emailId;
    //private Set<Reservation> reservations;
}
