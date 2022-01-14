package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Order;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Order placeOrder(String balloonColor,String balloonSize, String username,LocalDateTime orderDate) {
        User user;
        if(this.userRepository.findByUsername(username).isPresent()){
            user = this.userRepository.findByUsername(username).get();
        }
        else {
            user = new User(username);
            this.userRepository.save(user);
        }
        return this.orderRepository.save(new Order(balloonColor,balloonSize,orderDate,user));
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public List<Order> searchByOrderAttribute(String text) {
        return this.orderRepository.findOrdersBySearch(text);
    }

}
