package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.RestaurantGateway;

public class GetRestaurantByIdUseCase {
  private final RestaurantGateway restaurantGateway;

  public GetRestaurantByIdUseCase(RestaurantGateway restaurantGateway) {
    this.restaurantGateway = restaurantGateway;
  }

  public Restaurant execute(Long restaurantId) {
    return restaurantGateway
        .findRestaurantById(restaurantId)
        .orElseThrow(() -> new NotFoundException("Restaurante não encontrado"));
  }
}
