package pl.restaurant.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.restaurant.app.entity.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Override
    List<City> findAll();
}
