package com.example.cardealershipapp.service.impl;

import com.example.cardealershipapp.model.Application;
import com.example.cardealershipapp.model.Car;
import com.example.cardealershipapp.model.User;
import com.example.cardealershipapp.repository.ApplicationRepository;
import com.example.cardealershipapp.service.ApplicationService;
import com.example.cardealershipapp.service.CarService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CarService carService;

    public ApplicationServiceImpl(ApplicationRepository userCarRepository, CarService carService) {
        this.applicationRepository = userCarRepository;
        this.carService = carService;
    }

    @Override
    public Double calculateRentalPrice(LocalDateTime rentedFrom, LocalDateTime rentedTo, Double price) {

        Long dur = Duration.between(rentedFrom, rentedTo).toDays();

        return dur*price;
    }

    @Override
    public List<Application> findAll() {
        return this.applicationRepository.findAll();
    }

    @Override
    public List<Application> listActiveRentals() {
        return this.applicationRepository.findAllByActiveIsTrue();
    }

    @Override
    public Application create(User user, Car car, LocalDateTime from, LocalDateTime to) {
        this.carService.updateRent(car, true);
        return this.applicationRepository.save(new Application(user, car, from, to));
    }
}
