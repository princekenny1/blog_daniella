package com.example.demo.controller;

import com.example.demo.exceptions.GlobalException;
import com.example.demo.model.Blog;
import com.example.demo.model.Comment;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/add/comment/{id}")
    public String addComment(Model model, @PathVariable String id, @Valid Comment comment) {
        if(id ==null || id.equals(""))
            return "redirect:/";
        Blog blog = blogService.getBlogById(id);
        Comment comment1 = new Comment();
        comment1.setBlog(blog);
        comment1.setComment(comment.getComment());
        comment1.setCommentAuthor(comment.getCommentAuthor());
        commentService.saveComment(comment1);

        return "redirect:/blog/"+id;
    }

    @RequestMapping("/comment/{id}")
    public String getComments(Model model, @PathVariable String id) {
        if(id ==null || id.equals(""))
            return "redirect:/blogs";
        Blog blog = blogService.getBlogById(id);
        List<Comment> comments = blog.getCommentList();
        model.addAttribute("comments",comments);
        return "comments";
    }
}
