package com.fiuza.great.food.helper.dto.item;

import com.fiuza.great.food.core.dto.request.item.ItemDto;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.enums.KitchenType;

import java.math.BigDecimal;

public class ItemDtoHelper {
    public static ItemDto defaultDto() {
        return new ItemDto(
                1L,
                "Vatapá",
                "Gostoso de mais",
                new BigDecimal("23.00"),
                true,
                "/foto-do-vatapa.jpg"
        );
    }
}
