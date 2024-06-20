package com.dame.ecommece.service;

import com.dame.ecommece.entity.Basket;
import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.repository.BasketItemRepository;
import com.dame.ecommece.repository.BasketRepository;
import com.dame.ecommece.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

