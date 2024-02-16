package com.example.cardealershipapp.web.controller;


import com.example.cardealershipapp.model.Car;
import com.example.cardealershipapp.model.User;
import com.example.cardealershipapp.service.ApplicationService;
import com.example.cardealershipapp.service.CarService;
import com.example.cardealershipapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    public final CarService carService;
    public final UserService userService;
    public final ApplicationService applicationService;

    public ApplicationController(CarService carService,
                                 UserService userService,
                                 ApplicationService applicationService) {
        this.carService = carService;
        this.userService = userService;
        this.applicationService = applicationService;
    }

    @GetMapping
    public String showRentalsPage(Model model) {

        model.addAttribute("rentals", this.applicationService.listActiveRentals());
        return "";
    }

    @PostMapping("/rent/{id}")
    public String rent(@PathVariable Long id,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime rentedFrom,
                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime rentedTo,
                       HttpServletRequest req) {

        String username = req.getRemoteUser();

        User user = this.userService.findUserByEmail(username);
        Car car = this.carService.findById(id);

        this.applicationService.create(user, car, rentedFrom, rentedTo);
        return "redirect:/home";
    }

}
