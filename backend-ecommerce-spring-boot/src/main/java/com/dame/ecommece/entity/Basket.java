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
    private List<BasketItem> items = new ArrayList<>();

    public Basket() {
    }

    public Basket(String nameBasket) {
        this.nameBasket = nameBasket;
    }

    //        @OneToOne
//    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public String getNameBasket() {
        return nameBasket;
    }

    public void setNameBasket(String nameBasket) {
        this.nameBasket = nameBasket;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", nameBasket='" + nameBasket + '\'' +
                ", items=" + items +
                '}';
    }
}
