package com.example.rentcar.rentalCar.service;

import com.example.rentcar.car.entity.Car;
import com.example.rentcar.car.repository.CarRepository;
import com.example.rentcar.rentalCar.dto.RentalResponseDto;
import com.example.rentcar.rentalCar.dto.RentalUpdateDto;
import com.example.rentcar.rentalCar.entity.RentalRecord;
import com.example.rentcar.rentalCar.repository.RentalRepository;
import com.example.rentcar.user.entity.User;
import com.example.rentcar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    public RentalResponseDto create(RentalResponseDto responseDto) {

        UUID carId = responseDto.getCarId();

        Optional<Car> byId = carRepository.findById(carId);

        if (byId.isEmpty()){
            System.out.println("Car not found");
            return null;
        }

        if(!byId.get().getIsSpoiled()){
            System.out.println("The car is broken");
        }

        LocalDateTime rentalStartTime = responseDto.getRentalStartTime();
        LocalDateTime rentalEndTime = responseDto.getRentalEndTime();

        LocalDateTime now = LocalDateTime.now();
        if(rentalStartTime.isBefore(now)){
            System.out.println("Kiritilgan sana to`g`ri emas!!!");
            return null;
        }

        List<RentalRecord> carIdAll = getCarIdAll(responseDto.getCarId());
        boolean t=false;
        if(carIdAll.size()==0){
            RentalRecord record = mapper.map(responseDto, RentalRecord.class);
            return  mapper.map(rentalRepository.save(record),RentalResponseDto.class);
        }
        for (int i = 0; i < carIdAll.size(); i++) {
            if(!rentalStartTime.isAfter(carIdAll.get(i).getRentalStartTime())){
                t=true;
                break;
            }
        }

        if(t){
            System.out.println("Bu paytga buyurtm olingan. Boshqasana kiriting");
        }

        RentalRecord record = mapper.map(responseDto, RentalRecord.class);
        record = rentalRepository.save(record);

        return mapper.map(record,RentalResponseDto.class);
    }

    public List<RentalResponseDto> getRentals() {
        List<RentalRecord> all = rentalRepository.findAll();
        return all.stream()
                .map(rentalRecord -> mapper.map(rentalRecord, RentalResponseDto.class))
                .toList();
    }

    public List<RentalRecord> getCarIdAll(UUID id) {

        List<RentalRecord> all = rentalRepository.findAll();
        ArrayList<RentalRecord> rentalRecords = new ArrayList<>();

        for (RentalRecord record : all) {
            if(record.getCar().getId().equals(id)){
                rentalRecords.add(record);
            }
        }

        return rentalRecords;
    }
    public RentalResponseDto getRental(UUID id) {
        Optional<RentalRecord> byId = rentalRepository.findById(id);
        if (byId.isEmpty()) {
            System.out.println("Rental not found");
            return null;
        }
        return mapper.map(byId.get(), RentalResponseDto.class);
    }


    public RentalResponseDto update(UUID id, RentalUpdateDto rentalUpdateDto) {
        Optional<RentalRecord> byId = rentalRepository.findById(id);

        if (byId.isEmpty()) {
            System.out.println("User not found");
        }

        Optional<Car> byIdCar = carRepository.findById(rentalUpdateDto.getCarId());
        if (byIdCar.isEmpty()) {
            System.out.println("Car not found");
            return null;
        }
        Optional<User> byIdUser = userRepository.findById(rentalUpdateDto.getUserId());
        if (byIdUser.isEmpty()) {
            System.out.println("User not found");
            return null;
        }

        RentalRecord rentalRecord = byId.get();
        rentalRecord.setCar(byIdCar.get());
        rentalRecord.setUser(byIdUser.get());
        rentalRecord.setRentalPrice(rentalRecord.getRentalPrice());
        rentalRecord.setRentalStartTime(rentalRecord.getRentalStartTime());
        rentalRecord.setRentalEndTime(rentalRecord.getRentalEndTime());

        rentalRepository.save(rentalRecord);

        RentalResponseDto rentalResponseDto = new RentalResponseDto();

        rentalResponseDto.setUserId(rentalUpdateDto.getUserId());
        rentalResponseDto.setCarId(rentalUpdateDto.getCarId());
        rentalResponseDto.setRentalPrice(rentalUpdateDto.getRentalPrice());
        rentalResponseDto.setRentalStartTime(rentalUpdateDto.getRentalStartTime());
        rentalResponseDto.setRentalEndTime(rentalUpdateDto.getRentalEndTime());

        return rentalResponseDto;
    }

    public void delete(UUID id) {
        rentalRepository.deleteById(id);
    }
}
