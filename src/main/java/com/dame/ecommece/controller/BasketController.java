package com.dame.ecommece.controller;

import com.dame.ecommece.entity.Basket;
import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.entity.Product;
import com.dame.ecommece.repository.BasketItemRepository;
import com.dame.ecommece.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// BasketController.java
@RestController
@RequestMapping("/api/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;


    @PostMapping
    public ResponseEntity<Basket> addBasket(@RequestParam String nameBasket) {
        Basket newBasket = basketService.addBasket(nameBasket);
        return ResponseEntity.ok().body(newBasket);
    }

    @GetMapping("/{basketId}")
    public ResponseEntity<Basket> getBasketById(@PathVariable Long basketId) {
        Basket basket = basketService.loadBasketById(basketId);
        return ResponseEntity.ok().body(basket);
    }

    @PostMapping("/{basketId}/items")
    public ResponseEntity<Void> addItemToBasket(@PathVariable Long basketId,
                                                @RequestParam Long productId,
                                                @RequestParam int quantity) {
        basketService.addItemToBasket(basketId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{basketId}/items")
    public ResponseEntity<Void> removeItemFromBasket(@PathVariable Long basketId,
                                                     @RequestParam Long productId,
                                                     @RequestParam int quantity) {
        basketService.removeItemFromBasket(basketId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    // Endpoint to calculate total price of items in a basket
    @GetMapping("/{basketId}/totalPrice")
    public ResponseEntity<Double> calculateTotalPrice(@PathVariable Long basketId) {
        double totalPrice = basketService.calculateTotalPrice(basketId);
        return ResponseEntity.ok().body(totalPrice);
    }
}

