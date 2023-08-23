package com.example.iber.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
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
}