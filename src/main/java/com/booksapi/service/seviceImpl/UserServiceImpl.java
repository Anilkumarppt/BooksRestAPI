package com.booksapi.service.seviceImpl;

import com.booksapi.exception.ResourceNotFoundEx;
import com.booksapi.model.dto.UserDto;
import com.booksapi.model.entities.BooksUser;
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
        BooksUser newBooksUser = mapper.map(user, BooksUser.class);

        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        newBooksUser.setEmailId(user.getEmailId());
        newBooksUser.setPassword(encodedPassword);
        newBooksUser.setEmailId(user.getEmailId());
        newBooksUser.setUsername(user.getUsername());
        BooksUser createdBooksUser = userRepository.save(newBooksUser);
        UserDto userDto = mapper.map(createdBooksUser, UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<BooksUser> booksUserList = this.userRepository.findAll();
        // converting list of user to list of user dtos with help of stream api
        List<UserDto> userDtoList = booksUserList.stream().map(user -> this.mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtoList;
    }


    @Override
    public UserDto getUser(int userId) throws Exception {
        BooksUser booksUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundEx("User Not found!", HttpStatus.NOT_FOUND));
        return mapper.map(booksUser, UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDetail, int userId) {
        BooksUser booksUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundEx("User Not found!", HttpStatus.NOT_FOUND));
        ;
        booksUser.setUsername(userDetail.getUsername());
        booksUser.setPassword(userDetail.getPassword());
        booksUser.setEmailId(userDetail.getEmailId());
        booksUser.setId(userDetail.getId());
        return mapper.map(booksUser, UserDto.class);
    }

    @Override
    public void deleteUser(int userId) {
        BooksUser booksUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundEx("User not found", HttpStatus.NOT_FOUND));
        userRepository.delete(booksUser);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        BooksUser booksUser = this.userRepository.findByEmailId(email).orElseThrow(() -> new ResourceNotFoundEx("User Not found!", HttpStatus.NOT_FOUND));
        ;
        return mapper.map(booksUser, UserDto.class);
    }
}
