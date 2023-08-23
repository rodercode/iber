package com.example.iber.controller;
import com.example.iber.model.Driver;
import com.example.iber.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping("/drivers")
    List<Driver> getAll() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Optional<Driver> driver = driverService.findDriverById(id);
        if (driver.isPresent()) {
            return ResponseEntity.ok(driver.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

