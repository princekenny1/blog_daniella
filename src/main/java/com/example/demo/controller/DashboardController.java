package com.example.demo.controller;

import com.example.demo.exceptions.GlobalException;
import com.example.demo.model.Blog;
import com.example.demo.model.Comment;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping("/dashboard")
    public String getDashboard(HttpServletRequest request, Model model) {
        List<Blog> blogs = blogService.getAllByUserId("__");
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10
        String search = "";
        System.out.println("search "+search);
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));

        }

        model.addAttribute("blogs", blogRepository.findAll(PageRequest.of(page, size)));
        return "dashboard";
    }

    @RequestMapping("/addBlog")
    public String getAddBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "blogEdit";
    }

    @RequestMapping("/blog/{id}")
    public String getOneBlog(Model model, @PathVariable String id) {
        if (id == null || id.equals(""))
            return "redirect:/blogs";
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", blog.getCommentList());
        return "blog";
    }

    @RequestMapping("/blog/delete/{id}")
    public String deleteBlog(Model model, @PathVariable String id) {
        if (id == null || id.equals(""))
            return "redirect:/dashboard";
        Blog blog = blogService.getBlogById(id);
        blog.setDeleted(true);
        blogService.save(blog);
        return "redirect:/dashboard";
    }

    @RequestMapping("/blog/update/")
    public String updateBlog(Model model, @Valid Blog blog) {
        if (blog == null)
            return "redirect:/blog/" + blog.getId();
        Blog updated = blogService.getBlogById(blog.getId());
        if (updated == null)
            throw new GlobalException("");
        blogService.save(blog);
        return "redirect:/dashboard";
    }

    @PostMapping("/saveBlog")
    public String saveBlog(Model model, @ModelAttribute("blog") Blog blog) {
        try {
            String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/demo/src/main/resources/static/uploads";
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, blog.getMultipartFile().getOriginalFilename());
            Files.copy(blog.getMultipartFile().getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);
            blog.setImageName(blog.getMultipartFile().getOriginalFilename());
            blogService.save(blog);
            return "redirect:/dashboard";
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "redirect:/addBlog";
        }
    }
}
