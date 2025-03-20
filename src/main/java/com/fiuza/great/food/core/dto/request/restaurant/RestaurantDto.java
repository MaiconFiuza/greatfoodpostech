package com.fiuza.great.food.core.dto.request.restaurant;

import com.fiuza.great.food.core.enums.KitchenType;

public record RestaurantDto(
    String ownerEmail, String name, KitchenType kitchenType, String openingHours) {}
