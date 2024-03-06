package com.okayjava.html.service;

import com.okayjava.html.model.User;
import com.okayjava.html.repo.UserRepository;
import com.okayjava.html.service.UserService;
import com.okayjava.html.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}