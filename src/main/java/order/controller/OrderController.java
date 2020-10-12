package order.controller;

import javassist.NotFoundException;
import order.dto.CreateOrderDTO;
import order.dto.Customer;
import order.dto.ThingDTO;
import order.entities.Order;
import order.entities.OrderThing;
import order.service.OrderService;
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
