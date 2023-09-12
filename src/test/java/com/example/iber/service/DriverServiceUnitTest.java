package com.example.iber.service;
import com.example.iber.model.Driver;
import com.example.iber.repo.DriverRepo;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

// Tell JUnit to use the Mockito extension
@ExtendWith(MockitoExtension.class)
class DriverServiceUnitTest {

    @Mock
    private DriverRepo driverRepo;

    @InjectMocks
    private DriverService driverService;

    private Driver driverOne;
    private Driver driverTwo;

    @BeforeEach
    public void setup(){
        // Create a driver object
        driverOne = Driver.builder()
                .name("Johan Larsson")
                .rating(5)
                .car("Tesla")
                .carLicense("345 sfr")
                .location("Luleå City")
                .passengerCapacity(4)
                .build();

        // Create a driver object
        driverTwo = Driver.builder()
                .name("Peter Kaffesoon")
                .rating(3)
                .car("Volvo")
                .carLicense("357 sde")
                .location("Piteå City")
                .passengerCapacity(3)
                .build();
    }


    @Test
    public void Should_ReturnDriver_When_CreateDriver(){

        // Arrange - given that the driverRepo.save() method will return driverOne
        given(driverRepo.save(driverOne)).willReturn(driverOne);

        // test createDriver()
        Driver savedDriver = driverService.createDriver(driverOne);

        // Make sure it returns driver
        assertThat(savedDriver).isNotNull();
        verify(driverRepo, times(1)).save(driverOne);
        verifyNoMoreInteractions(driverRepo);
    }

    @Test
    public void findAllDrivers_ShouldReturnListOfDrivers_WhenDriversExist(){
        // Arrange
        given(driverRepo.findAll()).willReturn(List.of(driverOne, driverTwo));

        // Act
        List<Driver> getDrivers = driverService.findAllDrivers();

        // Assert
        assertThat(getDrivers).isNotNull();
        assertThat(getDrivers.size()).isEqualTo(2);
        verify(driverRepo, times(1)).findAll();
        verifyNoMoreInteractions(driverRepo);
    }

    @Test
    public void findDriverById_ShouldReturnDriver_WhenDriverExist(){
        // ARRANGE
        given(driverRepo.findById(1L)).willReturn(Optional.of(driverOne));

        // ACT
        Optional<Driver> fetchDriver = driverService.findDriverById(1L);

        // ASSERT
        assertThat(fetchDriver).isNotEmpty();
        verify(driverRepo, times(1)).findById(1L);
        verifyNoMoreInteractions(driverRepo);
    }

    @Test
    void findDriverById_ShouldThrowEntityNotFoundException_WhenDriverDoesNotExist() {
        // ARRANGE
        long driveId = 1L;
        given(driverRepo.findById(driveId)).willReturn(Optional.empty());

        // ACT
        assertThrows(EntityNotFoundException.class,
                () -> driverService.findDriverById(driveId));

        // ASSERT
        verify(driverRepo, times(1)).findById(driveId);
        verifyNoMoreInteractions(driverRepo);
    }

    @Test
    public void removeDriverById_ShouldReturnNoting_WhenDeleteDriver(){

        long driverId = 1L;
        willDoNothing().given(driverRepo).deleteById(driverId);


        // ACT
        driverService.removeDriverById(driverId);

        // Verify that the deleteById method is called only once
        verify(driverRepo, times(1)).deleteById(driverId);

        // Verify that no other methods are called on the driverRepo mock object
        verifyNoMoreInteractions(driverRepo);
    }

    @Test
    void removeDriverById_ShouldThrowEntityNotFoundException_WhenDriverDoesNotExist() {
        // ARRANGE
        long driveId = 1L;
        given(driverRepo.existsById(driveId)).willReturn(false);

        // ACT
        assertThrows(EntityNotFoundException.class,
                () -> driverService.removeDriverById(driveId));

        // ASSERT
        verify(driverRepo, times(1)).existsById(driveId);
        verifyNoMoreInteractions(driverRepo);
    }
}