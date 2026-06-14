package com.universal.payroll.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void saveUser(String userName, String password){
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        userRepository.save(newUser);
    }
}
