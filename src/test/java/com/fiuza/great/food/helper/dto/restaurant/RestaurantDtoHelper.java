package com.fiuza.great.food.helper.dto.restaurant;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;
import com.fiuza.great.food.core.enums.KitchenType;

public class RestaurantDtoHelper {
    public static RestaurantDto defaultDto() {
        return new RestaurantDto(
                "teste@teste.com.br",
                "bom de prato",
                KitchenType.NORDESTINA,
                "das 18:00 - 23:00"
        );
    }
}
