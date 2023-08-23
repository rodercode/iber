package com.example.iber.service;
import com.example.iber.model.Driver;
import com.example.iber.repo.DriverRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepo driverRepo;
    public DriverService(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public List<Driver> findAllDrivers(){
        return driverRepo.findAll();
    }

    public Optional<Driver> findDriverById(Long id){
        return driverRepo.findById(id);
    }
}
