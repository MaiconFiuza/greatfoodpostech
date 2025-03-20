package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantUpdateDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.RestaurantGateway;

public class UpdateRestaurantUseCase {

  private final RestaurantGateway restaurantGateway;

  public UpdateRestaurantUseCase(RestaurantGateway restaurantGateway) {
    this.restaurantGateway = restaurantGateway;
  }

  public Restaurant execute(RestaurantUpdateDto restaurantUpdateDto, Long id) {
    var restaurant =
        restaurantGateway
            .findRestaurantById(id)
            .orElseThrow(() -> new NotFoundException("Restaurante não encontrado"));
    Restaurant updatedRestaurant =
        new Restaurant(
            restaurant.getId(),
            restaurantUpdateDto.name(),
            restaurantUpdateDto.kitchenType(),
            restaurantUpdateDto.openingHours(),
            restaurant.getOwner());
    return restaurantGateway.update(updatedRestaurant);
  }
}
