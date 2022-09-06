package com.booksapi.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReservationDto {
    private int id;
    private int bookId;
    private int userId;
    private Date createdDate;
}
