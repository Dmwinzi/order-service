package com.example.microservices.Controller;


import com.example.microservices.Config.WebConfig;
import com.example.microservices.Model.Order;
import com.example.microservices.Repository.Orderrepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/orderservice")
public class Controller {

    @Autowired
    Orderrepo  orderrepo;

    @Autowired
    WebClient.Builder webconfig;


    @GetMapping("/")
    public  String  welcome(){

        return  " Order service Microservice";
    }



    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod")
    @Retry(name = "inventory")
    @PostMapping(value = "/placeorder")
    public String  placeorder(@RequestBody Order order){
        String code = order.orderitems.get(0).getCode();
        //call inventory service to check if order in stock  and place order
        Boolean result =  webconfig.build()
                .get()
                .uri("http://inventory-service/inventory/Stocks/"+code)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(result){
            orderrepo.save(order);
            return "Order saved";
        } else  {
           return "Product not found";
        }

    }

    public String fallBackMethod(Order order, RuntimeException runtimeException){
        return "Oops something went wrong!!!";
    }



}
