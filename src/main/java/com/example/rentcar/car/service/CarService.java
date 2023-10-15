package com.example.rentcar.car.service;

import com.example.rentcar.car.dto.CarCreateDto;
import com.example.rentcar.car.dto.CarResponseDto;
import com.example.rentcar.car.dto.CarUpdateDto;
import com.example.rentcar.car.entity.Car;
import com.example.rentcar.car.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarService {

    private final ModelMapper mapper = new ModelMapper();
    private final CarRepository carRepository;

    public CarResponseDto create(CarCreateDto carCreateDto) {
        Car car = mapper.map(carCreateDto, Car.class);
        car = carRepository.save(car);
        return mapper.map(car, CarResponseDto.class);
    }

    public List<CarResponseDto> getCars() {
        List<Car> all = carRepository.findAll();
        return all.stream()
                .map(car -> mapper.map(car, CarResponseDto.class))
                .toList();
    }

    // todo  getCar vs update

    public CarResponseDto getCar(UUID id) {
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()){
            System.out.println("Car not found");
            return null;
        }
        return mapper.map(byId.get(),CarResponseDto.class);
    }

    public CarResponseDto update(UUID id, CarUpdateDto carUpdateDto) {
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()) {
            System.out.println("Car not found");
            return null;
        }
        Car car = byId.get();
        car.setModel(carUpdateDto.getModel());
        car.setCarNumber(carUpdateDto.getCarNumber());
        car.setColor(carUpdateDto.getColor());
        car.setIsSpoiled(carUpdateDto.getIsSpoiled());
        car.setDailRentalPrice(carUpdateDto.getDailRentalPrice());


        carRepository.save(car);

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(car.getId());
        carResponseDto.setModel(car.getModel());
        carResponseDto.setCarNumber(car.getCarNumber());
        carResponseDto.setColor(car.getColor());
        carResponseDto.setIsSpoiled(car.getIsSpoiled());
        carResponseDto.setDailRentalPrice(car.getDailRentalPrice());
        return carResponseDto;
    }

    public void delete(UUID id) {
        carRepository.deleteById(id);
    }
}
