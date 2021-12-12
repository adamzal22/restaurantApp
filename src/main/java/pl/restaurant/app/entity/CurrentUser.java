package pl.restaurant.app.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class CurrentUser extends User {
    private final pl.restaurant.app.entity.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.restaurant.app.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.restaurant.app.entity.User getUser() {return user;}{
}
}