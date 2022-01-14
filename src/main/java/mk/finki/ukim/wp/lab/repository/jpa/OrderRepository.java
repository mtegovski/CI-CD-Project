package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select o from Order o where o.dateCreated between :fromDate and :toDate")
    List<Order> findAllOrdersBetween(LocalDateTime fromDate, LocalDateTime toDate);
    @Query("select o from Order o where lower(o.balloonColor) like %?1% or lower(o.balloonSize) like %?1% or lower(o.user.username) like %?1% or " +
            "o.balloonColor like %?1% or o.balloonSize like %?1% or o.user.username like %?1%")
    List<Order> findOrdersBySearch(String text);
}
