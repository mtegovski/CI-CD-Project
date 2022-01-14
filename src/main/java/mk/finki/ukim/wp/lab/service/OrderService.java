package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService{
    Order placeOrder(String balloonColor, String balloonSize, String clientName, LocalDateTime orderDate);
    List<Order> findAll();
    Optional<Order> findById(Long id);
    List<Order> searchByOrderAttribute(String text);
}