package com.fiuza.great.food.helper.entities.restaurant;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.enums.KitchenType;
import com.fiuza.great.food.helper.entities.user.UserHelper;

import java.util.Collections;

public class RestaurantHelper {

    public static Restaurant restaurantDefault() {
        return  new Restaurant(
               1L,
                "bom de prato",
                KitchenType.NORDESTINA,
                "das 18:00 - 23:00",
                UserHelper.createUserWithId(),
                Collections.emptyList()
        );
    }

    public static Restaurant restaurantWithoutId() {
        return  new Restaurant(
                "bom de prato",
                KitchenType.NORDESTINA,
                "das 18:00 - 23:00",
                UserHelper.createUserWithId()
        );
    }

    public static Restaurant restaurantUpdated() {
        return  new Restaurant(
                1L,
                "bom de prato sobre nova direção",
                KitchenType.NORDESTINA,
                "das 18:00 - 23:00",
                UserHelper.createUserWithId(),
                Collections.emptyList()
        );
    }
}
