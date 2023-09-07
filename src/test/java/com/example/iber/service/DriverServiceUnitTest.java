package com.example.iber.service;
import com.example.iber.model.Driver;
import com.example.iber.repo.DriverRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.when;

// Tell JUnit to use the Mockito extension
@ExtendWith(MockitoExtension.class)
class DriverServiceUnitTest {

    // Create a mock object of the DriverRepo
    @Mock
    private DriverRepo driverRepo;

    // Inject mock dependency driverRepo into driverService
    @InjectMocks
    private DriverService driverService;


    // Data used for testing
    Driver driverOne = Driver.builder()
            .name("Johan Larsson")
            .rating(5)
            .car("Tesla")
            .carLicense("345 sfr")
            .location("Luleå City")
            .passengerCapacity(4)
            .build();

    Driver driverTwo = Driver.builder()
            .name("Peter Kaffesoon")
            .rating(3)
            .car("Volvo")
            .carLicense("357 sde")
            .location("Piteå City")
            .passengerCapacity(3)
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
        // Define the behaviour of save method
        // it will check if the object passing in is matching Driver
        // When passing any drive object to driverRepo.save -> return driver

        given(driverRepo.save(driverOne)).willReturn(driverOne);

        // test createDriver()
        Driver savedDriver = driverService.createDriver(driverOne);

        // Make sure it returns driver
        assertThat(savedDriver).isNotNull();
    }

////////////////////////////////////////////////////
// TESTING GET ALL DRIVERS
///////////////////////////////////////////////////

    /*
    method being tested: findAllDrivers
    class being tested: driverService
    mocked object: driverRepo

    requirements
        1. Should return drivers
        2. return value should not be null
    */

    @Test
    public void Should_ReturnTwoDrivers_When_FetchAllDrivers(){
        // Arrange
        given(driverRepo.findAll()).willReturn(List.of(driverOne, driverTwo));

        // Act
        List<Driver> getDrivers = driverService.findAllDrivers();

        // Assert
        assertThat(getDrivers).isNotNull();
        assertThat(getDrivers.size()).isEqualTo(1);
    }

////////////////////////////////////////////////////
// TESTING GET ALL DRIVERS
///////////////////////////////////////////////////

    /*
    method being tested: findAllDrivers
    class being tested: driverService
    mocked object: driverRepo

    requirements
        1. Should return drivers
        2. return value should not be null
    */

}