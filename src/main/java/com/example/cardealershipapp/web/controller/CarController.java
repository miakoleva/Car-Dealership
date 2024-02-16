package com.example.cardealershipapp.web.controller;

import com.example.cardealershipapp.model.Car;
import com.example.cardealershipapp.service.CarService;
import com.example.cardealershipapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final UserService userService;

    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping
    public String getCarsPage(@RequestParam(required = false) String manufacturer,
                              @RequestParam(required = false) String carModel,
                              Model model){

        List<Car> cars;
        if (manufacturer == null && carModel == null) {
            cars = this.carService.findAll();
        } else {
            cars = this.carService.filter(manufacturer, carModel);
        }

        model.addAttribute("cars", cars);
        model.addAttribute("users", this.userService.findAll());
        return "cars";
    }

    @GetMapping("/details/{id}")
    public String getCarDetailsPage(@PathVariable Long id, Model model){

        Car car = this.carService.findById(id);
        model.addAttribute("car", car);
        return "car-details";
    }

    @GetMapping("/edit/{id}")
    public String editCarPage(@PathVariable Long id, Model model){

        Car car = this.carService.findById(id);
        model.addAttribute("car", car);
        return "add-car";
    }

    @GetMapping("/add-form")
    public String showAdd(Model model){
        List<Car> cars = this.carService.findAll();
        model.addAttribute("cars", cars);
        return "add-car";
    }

    @PostMapping
    public String create(@RequestParam String manufacturer,
                         @RequestParam String model,
                         @RequestParam String fuel,
                         @RequestParam Integer seats,
                         @RequestParam String description,
                         @RequestParam("primaryImage")MultipartFile mainMultipartFile,
                         @RequestParam Double pricePerDay) throws IOException {

        this.carService.crete(manufacturer, model, seats, fuel, description, mainMultipartFile, pricePerDay);
        return "redirect:/cars";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String manufacturer,
                         @RequestParam String model,
                         @RequestParam String fuel,
                         @RequestParam Integer seats,
                         @RequestParam String description,
                         @RequestParam("primaryImage")MultipartFile mainMultipartFile,
                         @RequestParam Double pricePerDay) throws IOException {
        this.carService.edit(id, manufacturer, model, seats, fuel, description, mainMultipartFile, pricePerDay);
        return "redirect:/cars";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id){
        this.carService.deleteById(id);
        return "redirect:/cars";
    }


}
