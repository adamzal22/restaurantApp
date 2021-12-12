package pl.restaurant.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.restaurant.app.entity.City;
import pl.restaurant.app.entity.Restaurant;
import pl.restaurant.app.entity.User;

import java.util.List;
import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAllByCity(City city);
    List<Restaurant> findAllByCityName(String name);
    List<Restaurant> findAllByName(String name);
    List<Restaurant> findAllByVendorId(Long id);
    List<Restaurant> findAllByVendor(User vendor);
}
