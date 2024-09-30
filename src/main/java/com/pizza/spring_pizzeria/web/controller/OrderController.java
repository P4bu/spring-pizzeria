package com.pizza.spring_pizzeria.web.controller;

import com.pizza.spring_pizzeria.persistence.entity.OrderEntity;
import com.pizza.spring_pizzeria.persistence.projection.OrderSumary;
import com.pizza.spring_pizzeria.service.OrderService;
import com.pizza.spring_pizzeria.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getToDayOrder() {
        return ResponseEntity.ok(this.orderService.getToDayOrder());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutSideOrders() {
        return ResponseEntity.ok(this.orderService.getOutSideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSumary> getSumary(@PathVariable int id) {
        return ResponseEntity.ok(this.orderService.getSumary(id));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto){
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}
