package com.dame.ecommece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
//@Table(name="basketItems")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    @JsonIgnore
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;



    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Basket getBasket() {return basket;}

    public void setBasket(Basket basket) {this.basket = basket;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}


    public Order getOrder() {return order;}

    public void setOrder(Order order) {this.order = order;}


// Method to calculate excluding tax and including tax for the order
    public double getTotalExcludeTaxe() {return product.getPrice() * quantity;}

    public double getTotalWithTaxe() {return product.getPrice() * quantity*1.2;}


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
