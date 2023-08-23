package com.example.iber.controller;
import com.example.iber.model.Driver;
import com.example.iber.service.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    List<Driver> getAllDrivers() {
        return driverService.findAllDrivers();
    }

    @GetMapping("/{id}")
    Optional<Driver> getDriver(@PathVariable Long id){
        return driverService.findDriverById(id);
    }

    @DeleteMapping("/{id}")
    String deleteDriver(@PathVariable Long id){
        driverService.removeDriverById(id);
        return "Driver was deleted successfully";
    }

}

