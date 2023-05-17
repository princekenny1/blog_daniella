package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Component
public interface UserRepository extends JpaRepository<User,String>{
    User findByUsername(String username);
}
