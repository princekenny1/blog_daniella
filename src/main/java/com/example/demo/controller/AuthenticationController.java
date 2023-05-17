package com.example.demo.controller;

import com.example.demo.model.Auth;
import com.example.demo.model.User;
import com.example.demo.service.EmailProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailProvider emailProvider;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String getLogin( Auth loginRequest, HttpSession session,
                           RedirectAttributes redirAttrs) {
//        userService.saveUser(new User("nelson@gmail.com","123456"));
        ModelAndView view = new ModelAndView();
        System.out.println(loginRequest);
//        view.addObject("auth", loginRequest);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            User userPrincipal = (User) authentication.getPrincipal();
            System.out.println(userPrincipal);
            session.setAttribute("username", userPrincipal.getUsername());
            view.setViewName("dashboard");
            return "redirect:/dashboard";
        } catch (Exception e) {
            // TODO: handle exception
            redirAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }

    @RequestMapping("/login")
    public String actionLogin(Model model,@ModelAttribute("user") Auth auth) {
        return "login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/login";
    }

    @RequestMapping("/signup")
    public String getSignup(Model model,@ModelAttribute("user") Auth auth) {
        return "signup";
    }
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String register(User user, HttpSession session,
                           RedirectAttributes redirAttrs,Model model) throws Exception {

            User savedUser = userService.saveUser(user);
            if(savedUser != null){
                String sendMessage =  emailProvider.sendConfirmationEmail(user.getUsername(),"Welcome to Blog APP");
                model.addAttribute("error","user saved");
                return "redirect:/login";
            }else{
                model.addAttribute("error","error in saving a user");
                return "redirect:/signup";
        }
    }


    @RequestMapping("/list")
    public String getAllUser(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "userList";
    }

    @RequestMapping(value = "/success")
    public String success(Model model) {
        return "success.html";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password, Model model) {
//        System.out.println("in controller "+username+"  "+password);
        userService.saveUser(new User(username, password));
        return "redirect:success";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam(value = "username") String username, @RequestParam(value = "id") String id,
            @RequestParam(value = "password") String password, Model model) {
//        System.out.println("in controller "+username+"  "+password);
        User user = new User(username, password);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") String id, Model model) {
        User user = new User();
        user.setId(id);
        userService.deleteUser(user);
        return "redirect:list";
    }


    @GetMapping("/edit/{id}")
    public String Update(@PathVariable(value = "id") String id, Model model) {
        System.out.println("in controller id " + id);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @RequestMapping("/access-denied")
    public String access(Model model) {
        System.out.println("in controller");
        return "access_denied";
    }

}
