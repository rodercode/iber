package com.example.iber.controller;
import com.example.iber.model.Driver;
import com.example.iber.service.DriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }
    @GetMapping
    List<Driver> getAll() {
        return driverService.getAllDrivers();
    }
}

