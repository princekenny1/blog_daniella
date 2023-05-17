package com.example.demo.repository;

import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,String> {

    @Query( value= "select * from Blog where user_id= ?1", nativeQuery = true)
    List<Blog> findAllByUserId(String userId);

    List<Blog> findAllByDeleted(boolean status);

    @Query(value = "select * from Blog s where s.title like %:keyword% or s.id like %:keyword%", nativeQuery = true)
    List<Blog> findByKeyword(@Param("keyword") String keyword);

}
