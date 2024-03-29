package com.dame.ecommece.controller;

import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.repository.BasketItemRepository;
import com.dame.ecommece.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class BasketItemsController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketItemRepository basketItemRepository;


    @GetMapping
    public List<BasketItem> getAllItems() {
        return basketService.getAllItems();
    }

}

