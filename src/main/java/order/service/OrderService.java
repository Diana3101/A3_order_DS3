package order.service;

import javassist.NotFoundException;
import order.dto.Customer;
import order.dto.ThingDTO;
import order.entities.Order;
import order.entities.OrderThing;
import order.repo.OrderRepository;
import order.repo.OrderThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private static final String SELLER_URL = "http://localhost:8089";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);
    private final OrderRepository orderRepository;
    private final OrderThingRepository orderThingRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderThingRepository orderThingRepository) {
        this.orderRepository = orderRepository;
        this.orderThingRepository = orderThingRepository;
    }

    @Transactional
    public boolean addOrder(List<ThingDTO> requiredThings, Customer customer) {
        List<ThingDTO> thingsToOrder = new ArrayList<>();

        for (ThingDTO th : requiredThings) {
            ThingDTO thing = getThingFromServiceByName(th.getName());
            int thingQuantity = thing.getQuantity();

            if (thingQuantity > 0) {
                thingsToOrder.add(thing);
                int newQuantity = thingQuantity - 1;
                th.setQuantity(newQuantity);
                saveNewQuantityForThing(th);
            } else {
                System.out.println("There is no " + th.getName() + ". It has been already sold.");
            }
        }
        return createOrder(thingsToOrder, customer);
    }

    private ThingDTO getThingFromServiceByName(String thingName) {
        ResponseEntity<ThingDTO> response = restTemplate
                .exchange(SELLER_URL + "/things/name=" + thingName,
                        HttpMethod.GET, headersEntity, ThingDTO.class);
        return response.getBody();
    }

    private void saveNewQuantityForThing(ThingDTO thingWithNewQuantity) {
        HttpEntity<ThingDTO> thing = new HttpEntity<>(thingWithNewQuantity);
        ResponseEntity<Void> response = restTemplate
                .exchange(SELLER_URL + "/things/newQuantity",
                        HttpMethod.PUT, thing, Void.class);
    }

    private boolean createOrder(List<ThingDTO> thingsToOrder, Customer customer) {
        boolean flag = false;
        UUID id = UUID.randomUUID();
        //double price = thingsToOrder.stream().mapToDouble(ThingDTO::getPrice).sum();
        Order order = new Order(id, customer.getCustomerId());
        for (ThingDTO th : thingsToOrder) {
            OrderThing orderThing = new OrderThing();
            orderThing.setOrderId(order.getId());
            orderThing.setThingId(th.getThingId());
            System.out.println("New order has created: " + order);
            orderRepository.save(order);
            orderThingRepository.save(orderThing);
            flag = true;
        }
        return flag;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<OrderThing> getAllOrderThings(){
        return orderThingRepository.findAll();
    }

    @Transactional
    public Order getOrderById(UUID id) throws NotFoundException {
        Optional<Order> tempOrder = orderRepository.findById(id);
        if (tempOrder.isPresent())
            return tempOrder.get();
        else
            throw new NotFoundException(String.format("Order with id %s was not found", id));
    }
}
