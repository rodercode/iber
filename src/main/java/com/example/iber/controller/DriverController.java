package com.example.iber.controller;
import com.example.iber.model.Driver;
import com.example.iber.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;
    @GetMapping("/drivers")
    List<Driver> getAll() {
        return driverService.getAllDrivers();
    }
}

