package com.example.order.repo;

import com.example.order.entities.OrderThing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderThingRepository extends JpaRepository<OrderThing, UUID> {
}
