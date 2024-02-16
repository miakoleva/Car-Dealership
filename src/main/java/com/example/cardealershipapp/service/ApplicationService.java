package com.example.cardealershipapp.service;

import com.example.cardealershipapp.model.Application;
import com.example.cardealershipapp.model.Car;
import com.example.cardealershipapp.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationService {

    Double calculateRentalPrice(LocalDateTime rentedFrom, LocalDateTime rentedTo, Double price);

    List<Application> findAll();

    List<Application> listActiveRentals();

    Application create(User user, Car car, LocalDateTime from, LocalDateTime to);
}
