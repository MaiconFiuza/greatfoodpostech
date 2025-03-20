package com.fiuza.great.food.core.dto.response.restaurant;

import com.fiuza.great.food.core.enums.KitchenType;
import java.util.List;

public record RestaurantDtoResponse(
    Long id,
    String name,
    KitchenType kitchenType,
    String openingHours,
    UserRestaurantDtoResponse owner,
    List<ItemRestaurantDtoResponse> items) {}
