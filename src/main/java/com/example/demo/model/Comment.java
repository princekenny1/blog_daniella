package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Comment {
    @Id
    private String id;
    private String commentAuthor;
    private String comment;
    private Date commentDate;
    @ManyToOne
    @JsonIgnore
    private Blog blog;

    @PrePersist
    public void beforeCreate(){
        id= UUID.randomUUID().toString();
        this.commentDate = new Date();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentAuthor='" + commentAuthor + '\'' +
                ", comment='" + comment + '\'' +
                ", commentDate=" + commentDate +
                ", blog=" + blog +
                '}';
    }
}
