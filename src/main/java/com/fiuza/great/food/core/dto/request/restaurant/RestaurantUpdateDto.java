package com.fiuza.great.food.core.dto.request.restaurant;

import com.fiuza.great.food.core.enums.KitchenType;

public record RestaurantUpdateDto(String name, KitchenType kitchenType, String openingHours) {}
