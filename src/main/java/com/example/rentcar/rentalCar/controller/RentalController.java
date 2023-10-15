package com.example.rentcar.rentalCar.controller;

import com.example.rentcar.rentalCar.dto.RentalResponseDto;
import com.example.rentcar.rentalCar.dto.RentalUpdateDto;
import com.example.rentcar.rentalCar.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentalCars")
public class RentalController {


    private final RentalService rentalService;


    @PostMapping
    public RentalResponseDto create(@RequestBody RentalResponseDto responseDto){
        return rentalService.create(responseDto);
    }

    @GetMapping
    public List<RentalResponseDto> getRentalCars(){
        return rentalService.getRentals();
    }

    @GetMapping("/{id}")
    public RentalResponseDto getRental(@PathVariable("id")UUID id){
        return rentalService.getRental(id);
    }

    @PutMapping("/{id}")
    public RentalResponseDto updateRental(@PathVariable("id") UUID id, @RequestBody RentalUpdateDto rentalUpdateDto){
        return rentalService.update(id,rentalUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable("id") UUID id){
        rentalService.delete(id);
    }
}
