package com.dame.ecommece.controller;

import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.repository.BasketItemRepository;
import com.dame.ecommece.service.BasketItemService;
import com.dame.ecommece.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket-items")
//@CrossOrigin(origins = "http://localhost:4200")
public class BasketItemsController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private BasketItemRepository basketItemRepository;


    @GetMapping
    public List<BasketItem> getAllItems() {
        return basketService.getAllItems();
    }

    @PostMapping("/{basketId}/items")
    public ResponseEntity<BasketItem> addItemToBasketJson(@RequestBody BasketItem basketItem) {
        BasketItem addBasketItem = basketItemService.addItemToBasket(basketItem);
        return new ResponseEntity<>(addBasketItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{basketId}/items")
    public ResponseEntity<Void> removeItemFromBasket(@RequestBody BasketItem basketItem) {
        basketItemService.removeItemFromBasket(basketItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear-all")
    public ResponseEntity<Void> clearAllBasketItems() {
        basketItemService.clearAllBasketItems();
        return ResponseEntity.ok().build();
    }


}

