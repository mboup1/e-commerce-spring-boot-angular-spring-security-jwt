package com.dame.ecommece.service;

import com.dame.ecommece.entity.Basket;
import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.entity.Order;
import com.dame.ecommece.entity.Product;
import com.dame.ecommece.repository.BasketItemRepository;
import com.dame.ecommece.repository.BasketRepository;
import com.dame.ecommece.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    public Basket loadBasketById(Long basketId) {
        return basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));
    }

    public List<BasketItem> getAllItems() {
        return basketItemRepository.findAll();
    }

    public BasketItem addItemToBasket(BasketItem basketItem) {
        Long basketId = 1L;
        Long productId = basketItem.getProduct().getIdProd();
        int quantity = basketItem.getQuantity();

        Basket basket = loadBasketById(basketId);
        Optional<Order> order = orderService.getOrderById(1L);
        System.out.println("Order : " + order);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Vérifier si le produit existe déjà dans le panier
        BasketItem existingItem = basket.getBasketItems().stream()
                .filter(item -> item.getProduct().getIdProd().equals(productId))
                .findFirst()
                .orElse(null);
//        BasketItem newItem = new BasketItem();

        if (existingItem != null) {
            // Mettre à jour la quantité de l'élément existant
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            // Mettre à jour l'élément dans la base de données
            basketItemRepository.save(existingItem);
            return existingItem;
        } else {
            // Ajouter un nouvel élément au panier
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

        Basket basket = loadBasketById(basketId);

        // Recherche de l'élément correspondant dans le panier
        BasketItem existingItem = basket.getBasketItems().stream()
                .filter(item -> item.getProduct().getIdProd().equals(productId))
                .findFirst()
                .orElse(null);
        System.out.println("existingItem : " + existingItem);

        if (existingItem != null) {
            int updatedQuantity = existingItem.getQuantity() - quantity;
            if (updatedQuantity <= 0) {
                // Si la quantité mise à jour est inférieure ou égale à zéro, supprimer l'élément du panier
                basket.getBasketItems().remove(existingItem);
                // Supprimer l'élément de la base de données
                basketItemRepository.delete(existingItem);
            } else {
                // Mettre à jour la quantité de l'élément
                existingItem.setQuantity(updatedQuantity);
                // Mettre à jour l'élément dans la base de données
                basketItemRepository.save(existingItem);
            }
        } else {
            // L'élément n'existe pas dans le panier
            throw new RuntimeException("Item not found in the basket");
        }

        // Mettre à jour le panier dans la base de données
        basketRepository.save(basket);
    }


    public double calculateTotalPrice(Long basketId) {
        Basket basket = loadBasketById(basketId);
        double totalPrice = 0.0;

        // Calculer le prix total en multipliant le prix de chaque produit par sa quantité dans le panier
        for (BasketItem item : basket.getBasketItems()) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity() * 1.2;
        }

        return totalPrice;
    }


//    public Basket addBasket(String nameBasket) {
//        Basket newBasket = new Basket();
//        newBasket.setNameBasket(nameBasket);
//        return basketRepository.save(newBasket);
//    }

    //    public void addItemToBasket(Long basketId, Long productId, int quantity) {
//
//        Basket basket = loadBasketById(basketId);
//        Optional<Order> order = orderService.getOrderById(1L);
//        System.out.println("Order : " + order);
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        // Vérifier si le produit existe déjà dans le panier
//        BasketItem existingItem = basket.getBasketItems().stream()
//                .filter(item -> item.getProduct().getIdProd().equals(productId))
//                .findFirst()
//                .orElse(null);
//
//        if (existingItem != null) {
//            // Mettre à jour la quantité de l'élément existant
//            existingItem.setQuantity(existingItem.getQuantity() + quantity);
//        } else {
//            // Ajouter un nouvel élément au panier
//            BasketItem newItem = new BasketItem();
//            newItem.setProduct(product);
//            newItem.setQuantity(quantity);
//            newItem.setBasket(basket);
//            newItem.setOrder(order.orElseThrow(() -> new RuntimeException("Order not found")));
//            basket.getBasketItems().add(newItem);
//
//            System.out.println("newItem :" + newItem);
//            basketItemRepository.save(newItem);
//        }
//
//        // Mettre à jour le panier dans la base de données
//        basketRepository.save(basket);
//    }
}

