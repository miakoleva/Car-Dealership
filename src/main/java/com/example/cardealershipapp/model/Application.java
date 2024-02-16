package com.example.cardealershipapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Application {
    //USER,CAR,FROM_TIME(LOCALDATETIME),TO_TIME(LOCALDATETIME), ACTIVE(BOOLEAN)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    private LocalDateTime rentedFromDate;
    private LocalDateTime rentedToDate;
    private Boolean active = true;

    public Application(User user, Car car, LocalDateTime rentedFromDate, LocalDateTime rentedToDate) {
        this.user = user;
        this.car = car;
        this.rentedFromDate = rentedFromDate;
        this.rentedToDate = rentedToDate;
    }
}
