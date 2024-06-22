package com.dame.ecommece.service;

import com.dame.ecommece.entity.Basket;
import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.repository.BasketItemRepository;
import com.dame.ecommece.repository.BasketRepository;
import com.dame.ecommece.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketItemRepository basketItemRepository;


    public Basket loadBasketById(Long basketId) {
        return basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));
    }

    public List<BasketItem> getAllItems() {
        return basketItemRepository.findAll();
    }

    public double calculateTotalPrice(Long basketId) {
        Basket basket = loadBasketById(basketId);
        double totalPrice = 0.0;

        for (BasketItem item : basket.getBasketItems()) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity() * 1.2;
        }

        return totalPrice;
    }
}

