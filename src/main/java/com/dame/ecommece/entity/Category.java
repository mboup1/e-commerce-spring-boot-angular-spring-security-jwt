package com.dame.ecommece.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;

    private String nameCat;
    private String descriptionCat;

    @OneToMany()
    @JsonIgnore
    private List<Product> products;

    public Category() {
    }

    public Category(String nameCat, String descriptionCat) {
        this.nameCat = nameCat;
        this.descriptionCat = descriptionCat;
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public String getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCat=" + idCat +
                ", nameCat='" + nameCat + '\'' +
                ", descriptionCat='" + descriptionCat + '\'' +
                '}';
    }
}
