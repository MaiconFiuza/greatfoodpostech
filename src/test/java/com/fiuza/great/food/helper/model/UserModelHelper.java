package com.fiuza.great.food.helper.model;

import com.fiuza.great.food.core.enums.UserType;
import com.fiuza.great.food.infra.model.UserModel;

import java.util.Date;

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
}
