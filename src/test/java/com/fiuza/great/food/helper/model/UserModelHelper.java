package com.fiuza.great.food.helper.model;

import com.fiuza.great.food.core.enums.UserType;
import com.fiuza.great.food.infra.model.UserModel;

import java.util.Date;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

public class UserModelHelper {

    public static UserModel createUserDefault() {
        return  new UserModel(
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                new Date(),
                "rua do teste",
                UserType.CUSTOMER
        );
    }

    public static UserModel createUserWithRestaurant() {
        return  new UserModel(
                1L,
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                new Date(),
                "rua do teste",
                UserType.CUSTOMER,
                listOf(RestaurantModelHelper.createRestaurantDefault())
        );
    }
}
