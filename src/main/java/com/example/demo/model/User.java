package com.example.demo.model;

import org.hibernate.sql.Update;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "userTable")
public class User implements UserDetails {
    @Id
    @Column(updatable = false)
    private String id = UUID.randomUUID().toString();
    private String username;
    private String password;
    private Date createdDate;
    private Date updateddDate;
    private boolean accountNonLocked;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    @PrePersist
    public void beforeCreate(){
      this.createdDate = new Date();
      this.accountNonLocked = true;
    }

    @PreUpdate
    public void beforeUpdate(){
        this.updateddDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateddDate() {
        return updateddDate;
    }

    public void setUpdateddDate(Date updateddDate) {
        this.updateddDate = updateddDate;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", updateddDate=" + updateddDate +
                ", blogList=" + blogList +
                '}';
    }
}
