package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.model.Comment;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment getCommentById(String id){
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()){
            return commentOptional.get();
        }else{
            throw new RuntimeException("There's no comment");
        }

    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
