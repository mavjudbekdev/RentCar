package com.example.rentcar.car.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String model;
    private int year;
    private double price;
    private String color;
    private String carNumber;
    private Boolean isSpoiled;
    private LocalDateTime dateSubmitted;
    private LocalDateTime dateReceived;


}
