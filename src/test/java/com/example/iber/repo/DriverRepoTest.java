package com.example.iber.repo;
import com.example.iber.model.Driver;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


// sets up an environment for testing your JPA repositories
@DataJpaTest

// Allow us to configure the behavior of the database used during integration testing.
// we config that we are going to test a h2 database
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DriverRepoTest {

    @Autowired DriverRepo driverRepo;

    // Data used for testing
    Driver driverOne = Driver.builder()
            .name("Johan Larsson")
                .rating(5)
                .car("Tesla")
                .carLicense("345 sfr")
                .location("Luleå City")
                .passengerCapacity(4)
                .build();

    // Data used for testing
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
    method being tested: driverRepo.save()
    requirements
        1. should be able to add driver to database
        2. return value should not be null
        3. driver's id should be greater than zero
    */


    @Test
    public void Should_NotReturnNull_When_DriverHasBeenSavedToDB(){
       // Act
       Driver savedDriver = driverRepo.save(driverOne);

       // Assert
       assertThat(savedDriver).isNotNull();
    }

    @Test
    public void NewDriverShouldNotHaveIDZero (){
        // Act
        Driver savedDriver = driverRepo.save(driverOne);

        // Assert
        assertThat(savedDriver.getId()).isGreaterThan(0);
    }


////////////////////////////////////////////////////
// TESTING FETCH ALL DRIVERS
///////////////////////////////////////////////////

    /*
    method being tested: driverRepo.findAll()
    requirements
        1. should be able to fetch all drivers from database
        2. driverList should have size of 2
        3. driverList should not be empty
    */


    @Test
    public void driverListShouldNotBeNull_When_Fetched(){
        // Arrange
        driverRepo.save(driverOne);
        driverRepo.save(driverTwo);

        // Act
        List<Driver> driverList = driverRepo.findAll();

        // Assert
        assertThat(driverList).isNotNull();
    }

    @Test
    public void Should_ReturnTwoDrivers_When_Fetched(){
        // Arrange
        driverRepo.save(driverOne);
        driverRepo.save(driverTwo);

        // Act
        List<Driver> driverList = driverRepo.findAll();

        // Assert
        assertThat(driverList).size().isEqualTo(2);
    }


////////////////////////////////////////////////////
// TESTING FETCH DRIVER BY ID
///////////////////////////////////////////////////

     /*
    method being tested: driverRepo.findById()
    requirements
        1. Should fetch driver by id
        2. Should return a driver object
        3. Should not be an empty object
    */


    @Test
    public void Should_NotBeEmpty_When_FetchDriverByIdOne(){
        // Arrange
        driverRepo.save(driverOne);
        driverRepo.save(driverTwo);

        // Act
        Optional<Driver> fetchedDriver = driverRepo.findById(4L);

        // Assert
         assertThat(fetchedDriver).isEmpty();
    }

    @Test
    public void Should_ReturnValueTesla_When_FetchDriverByIdOne(){
        // Arrange
        driverRepo.save(driverOne);
        driverRepo.save(driverTwo);

        // Act
        Optional<Driver> fetchedDriver = driverRepo.findById(1L);

        // Assert
        fetchedDriver.ifPresent(driver -> assertThat(driver.getCar()).isEqualTo("Tesla"));
    }


////////////////////////////////////////////////////
// TESTING FETCH DRIVER BY Car
///////////////////////////////////////////////////

     /*
    method being tested: driverRepo.findByCar()
    Description: is custom query method that should find a driver by car model

    requirements:
        1. Should fetch driver by car
        2. Should return a driver object
        3. Should not be an empty object
    */


    @Test
    public void Should_NotReturnEmpty_When_FetchingDriverByCar(){
        // Arrange
        driverRepo.save(driverOne);
        driverRepo.save(driverTwo);

        // Act
        Optional<Driver> fetchedDriver = driverRepo.findByCar("Tesla");

        // Assert
        assertThat(fetchedDriver).isNotEmpty();
    }

    @Test
    public void Should_ReturnDriverOne_When_FetchingCarNameTesla(){
        // Arrange
        driverRepo.save(driverOne);
        driverRepo.save(driverTwo);

        // Act
        Optional<Driver> fetchedDriver = driverRepo.findByCar("Tesla");

        // Assert
        fetchedDriver.ifPresent(driver -> assertThat(driver.getId()).isEqualTo(1L));
    }


////////////////////////////////////////////////////
// TESTING FETCH DRIVER BY Car
///////////////////////////////////////////////////

     /*
    method being tested: driverRepo.delete
    Description: a method that delete a driver by enter its ID

    requirements:
        1. Should delete driver by id
        2. Driver should not exist in the DB
    */


    @Test
    public void Should_ReturnEmpty_When_FetchNonExistDriver(){
        // Arrange
        driverRepo.save(driverOne);
        driverRepo.save(driverTwo);

        // Act
        long id = 1L;
        driverRepo.deleteById(id);
        Optional<Driver> fetchedDriver = driverRepo.findById(id);

        // Assert
        assertThat(fetchedDriver).isEmpty();
    }






}