package com.example.microservices.Controller;


import com.example.microservices.Config.WebConfig;
import com.example.microservices.Model.Entityresponse;
import com.example.microservices.Model.Order;
import com.example.microservices.Model.Orderitems;
import com.example.microservices.Repository.Orderrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RestController
@RequestMapping("/orderservice")
public class Controller {

    @Autowired
    Orderrepo  orderrepo;
    @Autowired
    WebConfig webConfig;

    @GetMapping("/")
    public  String  welcome(){

        return  " Order service Microservice";
    }


    @PostMapping(value = "/placeorder")
    public String  placeorder(@RequestBody Order order){

        String code = order.orderitems.get(0).getCode();

        //call inventory service to check if order in stock  and place order
        Boolean result =  webConfig.webClient()
                .get()
                .uri("http://localhost:2023/inventory/Stocks/"+code)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(result){
            orderrepo.save(order);
            return "Order saved";
        } else  {
            throw new IllegalArgumentException("Product not found");

        }

    }

}
