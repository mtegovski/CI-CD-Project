package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Order;
import mk.finki.ukim.wp.lab.model.ShoppingCart;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.OrderService;
import mk.finki.ukim.wp.lab.service.ShoppingCartService;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, OrderService orderService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @Override
    public List<Order> listAllOrders(Long cartId) {
        if(this.shoppingCartRepository.findById(cartId).isEmpty()) {
            throw new RuntimeException();
        }
        return this.shoppingCartRepository.findById(cartId).get().getOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(username));

        return this.shoppingCartRepository.findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addOrderToShoppingCart(String username, Long orderId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Order order = this.orderService.findById(orderId).orElseThrow(RuntimeException::new);
        if(shoppingCart.getOrders()
                .stream().filter(o->o.getId().equals(orderId))
                .collect(Collectors.toList()).size() > 0) {
            throw new RuntimeException();
        }
        shoppingCart.getOrders().add(order);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
