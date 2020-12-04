package com.example.order.controller.grpc;

import com.example.order.*;
import com.example.order.dto.Customer;
import com.example.order.dto.ThingDTO;
import com.example.order.entities.OrderThing;
import com.example.order.service.OrderService;
import com.example.order.entities.Order;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@GrpcService
public class OrderServiceImpl extends orderServiceGrpc.orderServiceImplBase{
    private final OrderService orderService;

    @Autowired
    public OrderServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void getOrder(GetRequestOrder request, StreamObserver<GetResponseOrder> responseStreamObserver) {
        List<Order> orders = orderService.getAllOrders();

        List<ProtoOrder> protoOrders = new ArrayList<>();
        for (Order order: orders) {
            ProtoOrder protoOrder = ProtoOrder.newBuilder()
                    .setId(order.getId().toString())
                    .setCustomerId(order.getCustomerId().toString())
                    .build();
            protoOrders.add(protoOrder);
        }
        GetResponseOrder response = GetResponseOrder.newBuilder().addAllOrder(protoOrders).build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void getOrderedThing(GetRequestOrderedThing request, StreamObserver<GetResponseOrderedThing> responseStreamObserver) {
        List<OrderThing> orderedThings = orderService.getAllOrderThings();

        List<ProtoOrderedThing> protoOrderedThings = new ArrayList<>();
        for (OrderThing orderedThing: orderedThings) {
            ProtoOrderedThing protoOrderedThing = ProtoOrderedThing.newBuilder()
                    .setThingId(orderedThing.getThingId().toString())
                    .setOrderId(orderedThing.getOrderId().toString())
                    .build();
            protoOrderedThings.add(protoOrderedThing);
        }
        GetResponseOrderedThing response = GetResponseOrderedThing.newBuilder().addAllOrderedThings(protoOrderedThings).build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseStreamObserver){
        ProtoCustomer protoCustomer = request.getCustomer();
        Customer customer = new Customer(UUID.randomUUID(), protoCustomer.getFirstName(), protoCustomer.getLastName());

        List<ProtoThing> protoThings = request.getThingsList();
        List<ThingDTO> things = new ArrayList<>();
        for (ProtoThing protoThing: protoThings) {
            ThingDTO thing = new ThingDTO(UUID.randomUUID(), protoThing.getName(), protoThing.getSize(), protoThing.getCondition(), protoThing.getPrice(), protoThing.getQuantity());
            things.add(thing);
        }
        orderService.addOrder(things, customer);

        CreateResponse response = CreateResponse.newBuilder()
                .build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}







