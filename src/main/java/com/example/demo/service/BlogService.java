package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public Blog getBlogById(String id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            return blogOptional.get();
        } else {
            throw new RuntimeException("Blog  can not be found");
        }
    }

    public List<Blog> getAll() {
        List<Blog> blogs = blogRepository.findAll().stream()
                .filter(blog -> !blog.isDeleted()).collect(Collectors.toList());
        for (int i=0 ; i < blogs.size(); i++) {
            String description = blogs.get(i).getDescription().subSequence(0, 200).toString();
            System.out.println("description "  +description);
            blogs.get(i).setDescription(description);
        }
        return blogs;

    }

    public List<Blog> getAllByUserId(String userId) {
//        List<Blog> blogs = blogRepository.findAllByUserId("1ccd22da-2571-4243-913e-bd75d7c057b7");
        List<Blog> blogs = getAll();
        return blogs;
    }

    public Blog save(Blog blog) {
        User user = userRepository.save(new User("dan", "123456"));
        blog.setUser(user);
        return blogRepository.save(blog);
    }

    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }
}
