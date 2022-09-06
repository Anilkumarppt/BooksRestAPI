package com.booksapi.service;

import com.booksapi.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

    UserDto getUser(int userId) throws Exception;

    UserDto update(UserDto userDetail, int userId);

    void deleteUser(int userId);

    UserDto getUserByEmail(String email);

}
