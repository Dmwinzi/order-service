package com.example.microservices.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "orderitems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orderitems {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    String code;
    int price;
    int quantity;


}
