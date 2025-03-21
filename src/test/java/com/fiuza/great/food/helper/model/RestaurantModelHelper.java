package com.fiuza.great.food.helper.model;

import com.fiuza.great.food.core.enums.KitchenType;
import com.fiuza.great.food.core.enums.UserType;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import com.fiuza.great.food.infra.model.RestaurantModel;
import com.fiuza.great.food.infra.model.UserModel;

import java.util.Collections;
import java.util.Date;

public class RestaurantModelHelper {
    public static RestaurantModel createRestaurantDefault() {
        return  new RestaurantModel(
                1L,
                "bom de prato",
                KitchenType.NORDESTINA,
                "das 18:00 - 23:00",
                UserModelHelper.createUserDefault(),
                Collections.emptyList()
        );
    }
}
