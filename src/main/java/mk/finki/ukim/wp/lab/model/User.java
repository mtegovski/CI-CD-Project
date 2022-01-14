package mk.finki.ukim.wp.lab.model;

import lombok.Data;
import mk.finki.ukim.wp.lab.bootstrap.UserFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "balloon_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;

    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User(String username, String password, UserFullname userFullname,LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.userFullname = userFullname;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }
}
