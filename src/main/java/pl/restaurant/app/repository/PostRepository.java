package pl.restaurant.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.restaurant.app.entity.City;
import pl.restaurant.app.entity.Post;
import pl.restaurant.app.entity.User;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByCity(City city);
    Post findPostByUser(User user);
    List<Post> findAllByUser(User vendor);

}
