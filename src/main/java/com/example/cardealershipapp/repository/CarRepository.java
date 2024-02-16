package com.example.cardealershipapp.repository;

import com.example.cardealershipapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByManufacturerAndModel(String manufacturer, String model);
    List<Car> findByManufacturer(String manufacturer);
    List<Car> findByModel(String model);
}
