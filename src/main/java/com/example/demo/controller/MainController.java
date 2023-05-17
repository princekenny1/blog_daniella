package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping("/")
    public String getLogin(Model model) {
       List<Blog> blogs = blogService.getAll();
       model.addAttribute("blogs",blogs);
        return "index.html";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        System.out.println("in controller");
        return "contact.html";
    }

    @RequestMapping("/blogs")
    public String service(Model model) {
        List<Blog> blogs = blogService.getAll();
        model.addAttribute("blogs",blogs);
        System.out.println("in controller");
        return "blogs";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        System.out.println("in controller");
        return "about.html";
    }


}
