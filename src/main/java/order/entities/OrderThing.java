package order.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class OrderThing {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID thingId;
    private UUID orderId;

    public OrderThing(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getThingId() {
        return thingId;
    }

    public void setThingId(UUID thingId) {
        this.thingId = thingId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
