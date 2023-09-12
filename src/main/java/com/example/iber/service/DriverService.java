package com.example.iber.service;
import com.example.iber.model.Driver;
import com.example.iber.repo.DriverRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepo driverRepo;

    public DriverService(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public List<Driver> findAllDrivers() {
        return driverRepo.findAll();
    }

    public Optional<Driver> findDriverById(Long id) {
        return Optional.ofNullable(driverRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Could not find driver with id: " + id
                )));
    }

    public Driver createDriver(Driver driver) {
        return driverRepo.save(driver);
    }

    public void removeDriverById(Long driverId) {
        if (!driverRepo.existsById(driverId)){
            throw new EntityNotFoundException(
                    "Could not find driver with id: " + driverId
            );
        }
        else {
           driverRepo.deleteById(driverId);
        }
    }
}
