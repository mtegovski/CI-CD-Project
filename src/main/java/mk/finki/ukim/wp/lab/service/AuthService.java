package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.User;

import java.time.LocalDate;
import java.util.Optional;

public interface AuthService {
    User save(User user);

    Optional<User> findByUsername(String username);

}
