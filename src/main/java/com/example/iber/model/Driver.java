package com.example.iber.model;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor

@Entity(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "car", nullable = false)
    private String car;

    @Column(name = "carLicense", nullable = false)
    private String carLicense;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "passengerCapacity", nullable = false)
    private int passengerCapacity;

}