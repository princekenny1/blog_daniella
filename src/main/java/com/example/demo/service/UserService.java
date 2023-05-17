package com.example.demo.service;

import com.example.demo.exceptions.GlobalException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User user) {
        try {

            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new GlobalException("username already used");
            }
            if (userRepository.findByUsername(user.getUsername()) == null) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                User savedUser = userRepository.save(user);
                return savedUser;
            }
        } catch (Exception e) {
            throw new GlobalException("error " + e.getMessage());
        }
        return null;
    }

    public void updateUser(User user) {
        User user1 = getUserById(user.getId());
        if (user1 == null)
            throw new RuntimeException("USE could not be found");
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        userRepository.save(user1);
    }

    public void deleteUser(User user) {
        User user1 = getUserById(user.getId());
        if (user1 == null)
            throw new RuntimeException("USE could not be found");
        userRepository.delete(user1);
    }

    public List<User> getAllUser() {
        List<User> list = userRepository.findAll();
        System.out.println(" the beat " + list);
        return userRepository.findAll();
    }

    public User getUserById(String id) {
//        User user = userRepository.findById(id).orElse(null);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("user can not be found");
        }

    }
}
