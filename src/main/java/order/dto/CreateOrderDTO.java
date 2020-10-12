package order.dto;

import java.util.List;

public class CreateOrderDTO {

    private Customer customerId;
    private List<ThingDTO> things;

    public Customer getCustomer() {
        return customerId;
    }

    public void setCustomer(Customer customerId) {
        this.customerId = customerId;
    }

    public List<ThingDTO> getThings() {
        return things;
    }

    public void setThings(List<ThingDTO> things) {
        this.things = things;
    }
}
