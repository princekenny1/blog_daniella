package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Blog {
    @Id
    @Column(updatable = false,insertable = false)
    private String id = UUID.randomUUID().toString();
    private String title;
    @Lob
    private String description;
    @JsonIgnore
    @Transient
    private MultipartFile multipartFile;

    private String imageName;
    @ManyToOne
    private User user;
    private Date creationDate;
    private boolean deleted;
    private Date updatedDate;
    @OneToMany(mappedBy = "blog")
    private List<Comment> commentList;

    @PrePersist
    public void beforeCreate(){
        this.creationDate = new Date();
    }

    @PreUpdate
    public void beforeUpdate(){
        this.updatedDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", creationDate=" + creationDate +
                ", commentList=" + commentList +
                '}';
    }
}
