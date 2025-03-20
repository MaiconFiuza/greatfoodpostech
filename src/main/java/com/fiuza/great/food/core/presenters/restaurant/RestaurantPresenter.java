package com.fiuza.great.food.core.presenters.restaurant;

import com.fiuza.great.food.core.dto.response.restaurant.ItemRestaurantDtoResponse;
import com.fiuza.great.food.core.dto.response.restaurant.RestaurantDtoResponse;
import com.fiuza.great.food.core.dto.response.restaurant.UserRestaurantDtoResponse;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import java.util.List;

public class RestaurantPresenter {

  public static RestaurantDtoResponse toRestaurantDtoResponse(Restaurant restaurant) {
    List<ItemRestaurantDtoResponse> items =
        restaurant.getItem().stream()
            .map(
                i ->
                    new ItemRestaurantDtoResponse(
                        i.getId(),
                        i.getName(),
                        i.getDescription(),
                        i.getPrice(),
                        i.isAvailableOnDeliveryOption(),
                        i.getPic()))
            .toList();
    UserRestaurantDtoResponse userRestaurantDtoResponse =
        new UserRestaurantDtoResponse(
            restaurant.getOwner().getId(),
            restaurant.getOwner().getName(),
            restaurant.getOwner().getEmail());
    return new RestaurantDtoResponse(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getKitchenType(),
        restaurant.getOpeningHours(),
        userRestaurantDtoResponse,
        items);
  }
}
