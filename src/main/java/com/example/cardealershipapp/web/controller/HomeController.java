package com.example.cardealershipapp.web.controller;

import com.example.cardealershipapp.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final CarService service;

    public HomeController(CarService service) {
        this.service = service;
    }


    @GetMapping()
    public String getHomePage(Model model){
        model.addAttribute("cars", service.findAll());
        return "index";
    }
}
