package com.fiuza.great.food.core.dto.response.item;

import java.math.BigDecimal;

public record ItemDtoResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    boolean isAvailableOnDeliveryOption,
    String pic,
    RestaurantItemDtoReponse restaurant) {}
