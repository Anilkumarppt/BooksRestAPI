package com.booksapi.service.seviceImpl;

import com.booksapi.exception.ResourceNotFoundEx;
import com.booksapi.model.entities.User;
import com.booksapi.model.dto.UserDto;
import com.booksapi.repository.UserRepository;
import com.booksapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        User newUser=mapper.map(user,User.class);

        String encodedPassword=this.passwordEncoder.encode(user.getPassword());
        newUser.setEmailId(user.getEmailId());
        newUser.setPassword(encodedPassword);
        newUser.setEmailId(user.getEmailId());
        newUser.setUsername(user.getUsername());
        User createdUser=userRepository.save(newUser);
        UserDto userDto=mapper.map(createdUser,UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> userList = this.userRepository.findAll();
        // converting list of user to list of user dtos with help of stream api
        List<UserDto> userDtoList = userList.stream().map(user -> this.mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtoList;
    }


    @Override
    public UserDto getUser(int userId) throws Exception {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundEx("User Not found!", HttpStatus.NOT_FOUND));
        return mapper.map(user,UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDetail, int userId) {
        User user =this.userRepository.findById(userId).get();
        user.setUsername(userDetail.getUsername());
        user.setPassword(userDetail.getPassword());
        user.setEmailId(userDetail.getEmailId());
        user.setId(userDetail.getId());
        return mapper.map(user,UserDto.class);
    }

    @Override
    public void deleteUser(int userId) {
        User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundEx("User not found",HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user=this.userRepository.findByEmailId(email).get();
        return mapper.map(user,UserDto.class);
    }
}
