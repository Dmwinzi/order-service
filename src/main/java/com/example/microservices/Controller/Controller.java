package com.example.microservices.Controller;


import com.example.microservices.Model.Order;
import com.example.microservices.Model.Orderitems;
import com.example.microservices.Repository.Orderrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orderservice")
public class Controller {

    @Autowired
    Orderrepo  orderrepo;


    @GetMapping("/")
    public  String  welcome(){

        return  " Order service Microservice";
    }


    @PostMapping(value = "/placeorder")
    public String  placeorder(@RequestBody Order order){
        orderrepo.save(order);
        return "Order placed";
    }



}
