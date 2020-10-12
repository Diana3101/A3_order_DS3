package order.dto;

import java.util.List;
import java.util.UUID;

public class ThingDTO {
    private UUID thingId;
    private String name;
    private Size size;
    private ConditionOfThing condition;
    private double price;
    private int quantity;

    public ThingDTO(){}

    public ThingDTO(UUID thingId, String name, Size size, ConditionOfThing condition, double price, int quantity) {
        this.thingId = thingId;
        this.name = name;
        this.size = size;
        this.condition = condition;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getThingId() {
        return thingId;
    }

    public void setThingId(UUID thingId) {
        this.thingId = thingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ConditionOfThing getCondition() {
        return condition;
    }

    public void setCondition(ConditionOfThing condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
