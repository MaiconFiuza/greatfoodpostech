package com.fiuza.great.food.core.dto.response.restaurant;

import java.math.BigDecimal;

public record ItemRestaurantDtoResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    boolean isAvailableOnDeliveryOption,
    String pic) {}
