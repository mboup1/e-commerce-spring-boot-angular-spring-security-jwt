package com.dame.ecommece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Basket basket;

    @ManyToOne
    private Product product;

    private int quantity;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Basket getBasket() {return basket;}

    public void setBasket(Basket basket) {this.basket = basket;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    @Override
    public String toString() {
        return "BasketItem{" +
                "id=" + id +
//                ", basket=" + basket +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
