package com.example.iber.service;

import com.example.iber.model.Driver;
import com.example.iber.repo.DriverRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverServiceUnitTest {

    @Mock
    private DriverRepo driverRepo;

    @InjectMocks
    private DriverService driverService;

    // Data used for testing
    Driver driver = Driver.builder()
            .name("Johan Larsson")
            .rating(5)
            .car("Tesla")
            .carLicense("345 sfr")
            .location("Luleå City")
            .passengerCapacity(4)
            .build();

////////////////////////////////////////////////////
// TESTING SAVE DRIVER
///////////////////////////////////////////////////

    /*
    method being tested: createDriver
    class being tested: driverService
    mocked object: driverRepo
    requirements
        1. should be able to add driver to database
        2. return value should not be null
        3. driver's id should be greater than zero
    */


    @Test
    public void Should_ReturnDriver_When_CreateDriver(){
        when(driverRepo.save(Mockito.any(Driver.class))).thenReturn(driver);
        Driver savedDriver = driverService.createDriver(driver);
        assertThat(savedDriver).isNotNull();
    }

}