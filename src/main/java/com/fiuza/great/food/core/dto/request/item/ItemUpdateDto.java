package com.fiuza.great.food.core.dto.request.item;

import java.math.BigDecimal;

public record ItemUpdateDto(
    String name,
    String description,
    BigDecimal price,
    boolean isAvailableOnDeliveryOption,
    String pic) {}
