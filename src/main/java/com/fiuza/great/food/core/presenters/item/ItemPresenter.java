package com.fiuza.great.food.core.presenters.item;

import com.fiuza.great.food.core.dto.response.item.ItemDtoResponse;
import com.fiuza.great.food.core.dto.response.item.RestaurantItemDtoReponse;
import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;

public class ItemPresenter {

  public static ItemDtoResponse toItemDtoResponse(Item item) {
    Restaurant restaurant = item.getRestaurant();
    RestaurantItemDtoReponse restaurantItemDtoReponse =
        new RestaurantItemDtoReponse(restaurant.getId(), restaurant.getName());
    return new ItemDtoResponse(
        item.getId(),
        item.getName(),
        item.getDescription(),
        item.getPrice(),
        item.isAvailableOnDeliveryOption(),
        item.getPic(),
        restaurantItemDtoReponse);
  }
}
