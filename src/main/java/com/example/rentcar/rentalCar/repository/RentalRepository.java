package com.example.rentcar.rentalCar.repository;

import com.example.rentcar.rentalCar.entity.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RentalRepository extends JpaRepository<RentalRecord, UUID> {

}
