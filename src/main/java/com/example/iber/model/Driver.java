package com.example.iber.model;
import jakarta.persistence.*;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rating;
    private String car;
    @Column(name = "carLicense")
    private String carLicense;
    private String location;
    @Column(name = "passengerCapacity")
    private int passengerCapacity;
    // Constructors, getters, setters, etc.
}