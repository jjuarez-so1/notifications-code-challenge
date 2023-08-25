package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.repository.UserRepository;
import com.jjuarez.gila.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
