package com.fiuza.great.food.helper.entities.item;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;

import java.math.BigDecimal;

public class ItemHelper {
    public static Item defaultDto(){
        return new Item(
            1L,
            "Vatapá",
            "Gostoso de mais",
            new BigDecimal("23.00"),
            true,
            "/foto-do-vatapa.jpg",
            RestaurantHelper.restaurantDefault()
        );
    }

    public static Item withouId(){
        return new Item(
                "Vatapá",
                "Gostoso de mais",
                new BigDecimal("23.00"),
                true,
                "/foto-do-vatapa.jpg",
                RestaurantHelper.restaurantDefault()
        );
    }

    public static Item moreExpensive(){
        return new Item(
                1L,
                "Vatapá",
                "Gostoso de mais",
                new BigDecimal("27.00"),
                true,
                "/foto-do-vatapa.jpg",
                RestaurantHelper.restaurantDefault()
        );
    }

}
