package com.example.iber.service;
import com.example.iber.model.Driver;
import com.example.iber.repo.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepo driverRepo;

    public List<Driver> getAllDrivers(){
        return driverRepo.findAll();
    }

    public Optional<Driver> findDriverById(Long id){
        return driverRepo.findById(id);
    }
}
