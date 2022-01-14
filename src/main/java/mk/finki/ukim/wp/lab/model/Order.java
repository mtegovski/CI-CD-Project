package mk.finki.ukim.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String balloonColor;

    private String balloonSize;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    public Order(String balloonColor, String balloonSize) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.dateCreated = LocalDateTime.now();
    }

    public Order(String balloonColor, String balloonSize,User user) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
    }

    public Order(String balloonColor, String balloonSize, LocalDateTime dateCreated, User user) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.dateCreated = dateCreated;
        this.user = user;
    }

    public Order() {
        this.dateCreated = LocalDateTime.now();
    }

    public String getDateAsString() {
        return this.dateCreated.toString();
    }
}
