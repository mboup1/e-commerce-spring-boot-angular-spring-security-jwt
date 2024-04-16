package com.dame.ecommece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;

    private String nameProd;
    private double price;
    private Date date;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BasketItem> basketItems = new ArrayList<>();


    public Product() {
    }

//    public Product(String nameProd, double price, Date date) {
//        this.nameProd = nameProd;
//        this.price = price;
//        this.date = date;
//    }

    public Product(String nameProd, double price, Date date, String imageUrl) {
        this.nameProd = nameProd;
        this.price = price;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public double getPriceItemWithTaxe() {return getPrice() *1.2;}

    @Override
    public String toString() {
        return "Product{" +
                "idProd=" + idProd +
                ", nameProd='" + nameProd + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
