package com.booksapi.model.dto;

import com.booksapi.model.entities.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private int id;
    private String username;
    private String password;
    private String emailId;
    //private Set<Reservation> reservations;
}
