package com.fiuza.great.food.helper.model;

import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
import com.fiuza.great.food.infra.model.ItemModel;
import com.fiuza.great.food.infra.model.RestaurantModel;

import java.math.BigDecimal;


public class ItemModelHelper {
    public static ItemModel createItemDefault() {
        return  new ItemModel(
                1L,
                "Vatapá",
                "Gostoso de mais",
                new BigDecimal("23.00"),
                true,
                "/foto-do-vatapa.jpg",
                RestaurantModelHelper.createRestaurantDefault()
        );
    }
}
