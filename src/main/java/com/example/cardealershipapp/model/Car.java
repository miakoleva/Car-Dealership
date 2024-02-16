package com.example.cardealershipapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Car {

    //MAKE, MODEL, SEATS, IS_RENTED(BOOLEAN), DESCRIPTION, IMAGE (STRING), RENT_PRICE_PER_DAY

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturer;
    private String model;
    private String fuel;
    private Integer seats;
    private Boolean isRented = false;
    private String description;
    private String image;
    private Double pricePerDay;

    public Car(String manufacturer, String model, Integer seats, String fuel, String description, String image, Double pricePerDay) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.seats = seats;
        this.fuel = fuel;
        this.description = description;
        this.image = image;
        this.pricePerDay = pricePerDay;
    }

    public Car() {

    }
}
