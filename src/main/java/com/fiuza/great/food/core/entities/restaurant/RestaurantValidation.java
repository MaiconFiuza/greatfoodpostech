package com.fiuza.great.food.core.entities.restaurant;

import com.fiuza.great.food.core.exceptions.NullDataNotNullException;

public class RestaurantValidation {
  public static void validation(Restaurant restaurant) {
    if (restaurant.getName() == null) {
      throw new NullDataNotNullException("name");
    }
    if (restaurant.getKitchenType() == null) {
      throw new NullDataNotNullException("kitchenType");
    }
    if (restaurant.getOwner() == null) {
      throw new NullDataNotNullException("owner");
    }
    if (restaurant.getOpeningHours() == null) {
      throw new NullDataNotNullException("openingHours");
    }
  }
}
