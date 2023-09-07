package com.example.iber.repo;
import com.example.iber.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<Driver,Long> {
    Optional<Driver> findByCar(String car);

}