package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.RestaurantGateway;

public class DeleteRestaurantUseCase {
  private final RestaurantGateway restaurantGateway;

  public DeleteRestaurantUseCase(RestaurantGateway restaurantGateway) {
    this.restaurantGateway = restaurantGateway;
  }

  public void execute(Long restaurantId) {
    Restaurant restaurant =
        restaurantGateway
            .findRestaurantById(restaurantId)
            .orElseThrow(() -> new NotFoundException("Restaurante não encontrado"));
    restaurantGateway.delete(restaurant.getId());
  }
}
