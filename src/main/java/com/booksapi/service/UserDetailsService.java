package com.booksapi.service;

import com.booksapi.model.entities.BooksUser;
import com.booksapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BooksUser booksUser = userDao.findByUsername(username);
        if (booksUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(booksUser.getUsername(), booksUser.getPassword(),
                new ArrayList<>());
    }

    public BooksUser save(BooksUser booksUser) {
        BooksUser newBooksUser = new BooksUser();
        newBooksUser.setUsername(booksUser.getUsername());
        newBooksUser.setPassword(bcryptEncoder.encode(booksUser.getPassword()));
        newBooksUser.setEmailId(booksUser.getEmailId());
        return userDao.save(newBooksUser);
    }
}