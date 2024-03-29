package com.dame.ecommece.controller;

import com.dame.ecommece.entity.Order;
import com.dame.ecommece.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
//        if (order.getItems() == null || order.getIdClient().isEmpty() ||
//                order.getDesignation() == null || order.getDesignation().isEmpty() ||
//                order.getNbDays() <= 0 ||
//                order.getUnitPrice() <= 0.0) {
//        }
//            return ResponseEntity.badRequest().body("Tous les champs doivent être remplis et valides");

        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
//        if (updatedOrder.getTypePresta() == null || updatedOrder.getTypePresta().isEmpty() ||
//                updatedOrder.getDesignation() == null || updatedOrder.getDesignation().isEmpty() ||
//                updatedOrder.getNbDays() <= 0 ||
//                updatedOrder.getUnitPrice() <= 0.0) {
//            return ResponseEntity.badRequest().body("Tous les champs doivent être remplis et valides");
//        }
        return orderService.updateOrder(id, updatedOrder)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.deleteOrder(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
