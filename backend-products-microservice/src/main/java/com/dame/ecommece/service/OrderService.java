package com.dame.ecommece.service;

import com.dame.ecommece.entity.BasketItem;
import com.dame.ecommece.entity.Client;
import com.dame.ecommece.entity.Order;
import com.dame.ecommece.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    ClientService clientService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BasketService basketService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order addOrder(Order order) {
        Long clientId = order.getIdClient();
        List<BasketItem> basketItemList = basketService.getAllItems();
        System.out.println("basketItemList : "+ basketItemList);
        Optional<Client> clientOptional = clientService.getClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            order.setClient(client);
            order.setBasketItems(basketItemList);

            order.setDate(new Date());

            return orderRepository.save(order);
        } else {
            // Gérer le cas où le client n'est pas trouvé
            throw new RuntimeException("Pas de client avec  l'id: " + clientId);
        }
    }


    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();

//            existingOrder.setTypePresta(updatedOrder.getTypePresta());
//            existingOrder.setDesignation(updatedOrder.getDesignation());
//            existingOrder.setDesignation(updatedOrder.getDesignation());
//            existingOrder.setNbDays(updatedOrder.getNbDays());
//            existingOrder.setUnitPrice(updatedOrder.getUnitPrice());
            existingOrder.setState(updatedOrder.getState());
            updatedOrder.setDate(new Date());

            return Optional.of(orderRepository.save(existingOrder));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
