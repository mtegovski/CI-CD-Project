package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Order;
import mk.finki.ukim.wp.lab.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Order> listAllOrders(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addOrderToShoppingCart(String username, Long orderId);
}
