package com.example.microservices.Repository;

import com.example.microservices.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Orderrepo  extends JpaRepository<Order,Long> {
}
