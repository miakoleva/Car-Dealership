package com.example.cardealershipapp.service;

import com.example.cardealershipapp.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();
    Car findById(Long id);
    List<Car> filter(String manufacturer, String model);
    Car crete(String manufacturer, String model, Integer seats, String fuel,
              String description, MultipartFile mainMultipartFile, Double pricePerDay) throws IOException;
    void deleteById(Long id);
    Car edit(Long id, String manufacturer, String model, Integer seats, String fuel,
                       String description, MultipartFile mainMultipartFile, Double pricePerDay) throws IOException;

    Car updateRent(Car car, Boolean status);
}
