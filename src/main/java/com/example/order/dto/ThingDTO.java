package com.example.order.dto;

import java.util.UUID;

public class ThingDTO {
    private UUID thingId;
    private String name;
    private String size;
    private String condition;
    private double price;
    private int quantity;

    public ThingDTO(){}

    public ThingDTO(UUID thingId, String name, String size, String condition, double price, int quantity) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
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
