package order.dto;

import order.entities.OrderThing;

import java.util.List;

public class OrderThingsDTO {
    private List<OrderThing> orderThings;

    public OrderThingsDTO(){}

    public List<OrderThing> getOrderThings() {
        return orderThings;
    }

    public void setOrderThings(List<OrderThing> orderThings) {
        this.orderThings = orderThings;
    }
}
