package com.fiuza.great.food.helper.dto.restaurant;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantUpdateDto;
import com.fiuza.great.food.core.enums.KitchenType;

public class RestaurantUpdatDtoHelper {
    public static RestaurantUpdateDto defaultDto(){
        return new RestaurantUpdateDto(
                "bom de prato sobre nova direção",
                KitchenType.NORDESTINA,
                "das 18:00 - 23:00"
        );
    }
}
