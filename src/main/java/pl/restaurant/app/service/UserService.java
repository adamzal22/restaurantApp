package pl.restaurant.app.service;

import org.springframework.stereotype.Service;
import pl.restaurant.app.entity.User;

@Service
public interface UserService {

    User findByLogin(String login);

    void saveUser(User user);
    void saveVendor(User user);
}
