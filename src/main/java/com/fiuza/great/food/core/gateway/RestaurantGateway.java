package com.fiuza.great.food.core.gateway;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import java.util.Optional;

public interface RestaurantGateway {

  Restaurant save(Restaurant restaurant);

  Optional<Restaurant> findRestaurantById(Long id);

  Restaurant update(Restaurant restaurant);

  void delete(Long id);
}
