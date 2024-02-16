package com.example.cardealershipapp.web.controller;

import com.example.cardealershipapp.model.User;
import com.example.cardealershipapp.model.exceptions.InvalidArgumentsException;
import com.example.cardealershipapp.model.exceptions.InvalidUserCredentialsException;
import com.example.cardealershipapp.service.AuthService;
import com.example.cardealershipapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final AuthService authService;

    public LoginController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user = null;

        try {
            user = this.authService.login(request.getParameter("username"), request.getParameter("password"));
        }catch (InvalidUserCredentialsException | InvalidArgumentsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }

}
