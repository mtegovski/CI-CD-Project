package mk.finki.ukim.wp.lab.repository.impl;


import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryOrderRepository {

    public List<Order> findAll() {
        return DataHolder.orders;
    }
}
