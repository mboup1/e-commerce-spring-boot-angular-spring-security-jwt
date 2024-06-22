package com.dame.ecommece.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameBasket;

    @OneToMany(mappedBy = "basket")
    private List<BasketItem> basketItems = new ArrayList<>();

    public Basket() {
    }

    public Basket(String nameBasket) {
        this.nameBasket = nameBasket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public String getNameBasket() {
        return nameBasket;
    }

    public void setNameBasket(String nameBasket) {
        this.nameBasket = nameBasket;
    }

    // Method to calculate the total number of items in the basket
    public int getTotalItems() {
        int totalItems = 0;
        if (basketItems != null) {
            for (BasketItem item : basketItems) {
                totalItems += item.getQuantity();
            }
        }
        return totalItems;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", nameBasket='" + nameBasket + '\'' +
                ", items=" + basketItems +
                '}';
    }
}
