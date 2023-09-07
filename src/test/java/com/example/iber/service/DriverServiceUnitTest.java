package com.example.iber.service;
import com.example.iber.model.Driver;
import com.example.iber.repo.DriverRepo;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

// Tell JUnit to use the Mockito extension
@ExtendWith(MockitoExtension.class)
class DriverServiceUnitTest {

    // Create a mock object of the DriverRepo
    @Mock
    private DriverRepo driverRepo;

    // Inject mock dependency driverRepo into driverService
    @InjectMocks
    private DriverService driverService;

    private Driver driverOne;
    private Driver driverTwo;

    @BeforeEach
    public void setup(){
        // Data used for testing
        driverOne = Driver.builder()
                .name("Johan Larsson")
                .rating(5)
                .car("Tesla")
                .carLicense("345 sfr")
                .location("Luleå City")
                .passengerCapacity(4)
                .build();

        driverTwo = Driver.builder()
                .name("Peter Kaffesoon")
                .rating(3)
                .car("Volvo")
                .carLicense("357 sde")
                .location("Piteå City")
                .passengerCapacity(3)
                .build();
    }





////////////////////////////////////////////////////
// TESTING CREATE DRIVER
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

        // setup behaviour for save method in driverRepo mock object
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

    requirements
        1. Should return drivers
        2. return value should not be null
    */

    @Test
    public void findAllDrivers_ShouldReturnListOfDrivers_WhenDriversExist(){
        // Arrange
        given(driverRepo.findAll()).willReturn(List.of(driverOne, driverTwo));

        // Act
        List<Driver> getDrivers = driverService.findAllDrivers();

        // Assert
        assertThat(getDrivers).isNotNull();
        assertThat(getDrivers.size()).isEqualTo(2);
    }

////////////////////////////////////////////////////
// TESTING GET DRIVER BY ID
///////////////////////////////////////////////////

    /*
    method being tested: findDriverById

    requirements
        1. Should return a driver when passing valid id
        2. And if driver exist in the database
        3. should not return null
    */

    @Test
    public void FindDriverById_ShouldReturnDriver_WhenDriverExist(){
        // Arrange
        given(driverRepo.findById(1L)).willReturn(Optional.of(driverOne));

        // Act
        Optional<Driver> fetchDriver = driverService.findDriverById(1L);

        // Assert
        assertThat(fetchDriver).isNotEmpty();
    }




}