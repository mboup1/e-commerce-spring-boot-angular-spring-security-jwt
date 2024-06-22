package com.dame.ecommece.service;

import com.dame.ecommece.entity.Basket;
import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.entity.Order;
import com.dame.ecommece.entity.Product;
import com.dame.ecommece.repository.BasketItemRepository;
import com.dame.ecommece.repository.BasketRepository;
import com.dame.ecommece.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketItemService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;

    public List<BasketItem> getAllItems() {
        return basketItemRepository.findAll();
    }

    public BasketItem addItemToBasket(BasketItem basketItem) {
        Long basketId = 1L;
        Long productId = basketItem.getProduct().getIdProd();
        int quantity = basketItem.getQuantity();

        Basket basket = basketService.loadBasketById(basketId);
        Optional<Order> order = orderService.getOrderById(1L);
        System.out.println("Order : " + order);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the product already exists in the basket
        BasketItem existingItem = basket.getBasketItems().stream()
                .filter(item -> item.getProduct().getIdProd().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Update the quantity of the existing item
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            // Update the item in the database
            basketItemRepository.save(existingItem);
            return existingItem;
        } else {
            // Add a new item to the basket
            BasketItem newItem = new BasketItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setBasket(basket);
            newItem.setOrder(order.orElseThrow(() -> new RuntimeException("Order not found")));
            basket.getBasketItems().add(newItem);

            System.out.println("newItem :" + newItem);
            basketItemRepository.save(newItem);
            return newItem;
        }
    }

    public void removeItemFromBasket(BasketItem basketItem) {
        Long basketId = 1L;
        Long productId = basketItem.getProduct().getIdProd();
        int quantity = basketItem.getQuantity();

        Basket basket = basketService.loadBasketById(basketId);

        // Search for the corresponding item in the basket
        BasketItem existingItem = basket.getBasketItems().stream()
                .filter(item -> item.getProduct().getIdProd().equals(productId))
                .findFirst()
                .orElse(null);
        System.out.println("existingItem : " + existingItem);

        if (existingItem != null) {
            int updatedQuantity = existingItem.getQuantity() - quantity;
            if (updatedQuantity <= 0) {
                // If the updated quantity is less than or equal to zero, remove the item from the basket
                basket.getBasketItems().remove(existingItem);
                // Delete the item from the database
                basketItemRepository.delete(existingItem);
            } else {
                // Update the quantity of the item
                existingItem.setQuantity(updatedQuantity);
                // Update the item in the database
                basketItemRepository.save(existingItem);
            }
        } else {
            // The item does not exist in the basket
            throw new RuntimeException("Item not found in the basket");
        }

        // Update the basket in the database
        basketRepository.save(basket);
    }

    public void clearAllBasketItems() {
        List<BasketItem> allBasketItems = basketItemRepository.findAll();
        basketItemRepository.deleteAll(allBasketItems);
    }
}
