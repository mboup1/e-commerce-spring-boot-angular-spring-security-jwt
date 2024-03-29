package com.dame.ecommece.entity;

import com.dame.ecommece.enums.OrderState;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idClient;

    @Enumerated(EnumType.ORDINAL)
//    @Column(columnDefinition = "int4")
    private OrderState state;

    @ManyToOne
    @JoinColumn(name = "client_id")
//    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<BasketItem> items = new ArrayList<>();

    public Order() { }

    public Order(Long idClient, OrderState state) {
        this.idClient = idClient;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderState getState() {return state;}

    public void setState(OrderState state) {this.state = state;}

    public Client getClient() {return client;}

    public void setClient(Client client) {this.client = client;}

    public Long getIdClient() {return idClient;}

    public void setIdClient(Long idClient) {this.idClient = idClient;}

    public List<BasketItem> getItems() {return items;}

    public void setItems(List<BasketItem> items) {this.items = items;}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + idClient +
                ", state=" + state +
//                ", client=" + client +
                '}';
    }
}