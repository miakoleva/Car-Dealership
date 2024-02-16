package com.example.cardealershipapp.service.impl;


import com.example.cardealershipapp.model.Car;
import com.example.cardealershipapp.model.exceptions.InvalidCarIdException;
import com.example.cardealershipapp.repository.CarRepository;
import com.example.cardealershipapp.service.CarService;
import com.example.cardealershipapp.web.controller.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return this.carRepository.findById(id)
                .orElseThrow(InvalidCarIdException::new);
    }

    @Override
    public List<Car> filter(String manufacturer, String model) {

        if(manufacturer!= null && model!=null){
            return this.carRepository.findByManufacturerAndModel(manufacturer, model);
        }else if(manufacturer!= null){
            return this.carRepository.findByManufacturer(manufacturer);
        }else if(model != null){
            return this.carRepository.findByModel(model);
        }
        return this.carRepository.findAll();
    }

    @Override
    public Car crete(String manufacturer, String model, Integer seats, String fuel, String description, MultipartFile mainMultipartFile, Double pricePerDay) throws IOException {

        String image = StringUtils.cleanPath(mainMultipartFile.getOriginalFilename());

        Car car = new Car(manufacturer, model, seats, fuel, description, image, pricePerDay);
        Car c  = this.carRepository.save(car);

        String uploadDir = "./src/main/resources/static/img/cars"+c.getId();
        FileUploadUtil.saveFile(uploadDir, mainMultipartFile, image);

        return c;
    }

    @Override
    public void deleteById(Long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public Car edit(Long id, String manufacturer, String model, Integer seats, String fuel, String description,MultipartFile mainMultipartFile, Double pricePerDay) throws IOException {

        String image = StringUtils.cleanPath(mainMultipartFile.getOriginalFilename());

        Car car = this.carRepository.findById(id).orElseThrow(InvalidCarIdException::new);

        car.setManufacturer(manufacturer);
        car.setModel(model);
        car.setSeats(seats);
        car.setFuel(fuel);
        car.setDescription(description);
        car.setImage(image);
        car.setPricePerDay(pricePerDay);

        Car c =  this.carRepository.save(car);
        String uploadDir = "./src/main/resources/static/img/cars"+c.getId();
        FileUploadUtil.saveFile(uploadDir, mainMultipartFile, image);
        return c;
    }

    @Override
    public Car updateRent(Car car, Boolean status) {
        car.setIsRented(status);
        return this.carRepository.save(car);
    }
}
