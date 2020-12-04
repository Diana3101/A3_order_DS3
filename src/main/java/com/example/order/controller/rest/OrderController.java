package com.example.order.controller.rest;

import com.example.order.dto.CreateOrderDTO;
import com.example.order.dto.Customer;
import com.example.order.dto.ThingDTO;
import com.example.order.entities.OrderThing;
import com.example.order.service.OrderService;
import javassist.NotFoundException;
import com.example.order.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> show(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping({"orderThings"})
    public ResponseEntity<List<OrderThing>> showThings(){
        return ResponseEntity.ok(orderService.getAllOrderThings());
    }

    @GetMapping({"id"})
    public ResponseEntity<Order> showById(@PathVariable UUID id) throws NotFoundException{
        return ResponseEntity.ok(orderService.getOrderById(id));
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOrderDTO createOrder){
        Customer customer = createOrder.getCustomer();
        System.out.println(customer.getCustomerId());
        List<ThingDTO> things = createOrder.getThings();
        if(orderService.addOrder(things, customer))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }


}
