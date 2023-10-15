package com.example.rentcar.car.controller;

import com.example.rentcar.car.dto.CarCreateDto;
import com.example.rentcar.car.dto.CarResponseDto;
import com.example.rentcar.car.dto.CarUpdateDto;
import com.example.rentcar.car.service.CarService;
import com.example.rentcar.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    public CarResponseDto createCar(@RequestBody CarCreateDto carCreateDto) {
        return carService.create(carCreateDto);
    }

    @GetMapping
    public List<CarResponseDto> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public CarResponseDto getCar(@PathVariable("id") UUID id) {
        return carService.getCar(id);
    }

    @PutMapping("/{id}")
    public CarResponseDto updateCar(@PathVariable("id") UUID id, @RequestBody CarUpdateDto carUpdateDto) {
        return carService.update(id, carUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") UUID id) {
        carService.delete(id);
    }

}
