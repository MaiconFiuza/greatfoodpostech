package com.fiuza.great.food.helper.entities.user;

import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.enums.UserType;

import java.util.Date;

import static java.util.Collections.emptyList;

public class UserHelper {

    public static User createUserDefault() {
        return  new User(
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                new Date(),
                "rua do teste",
                UserType.CUSTOMER
        );
    }

    public static User createUserWithId() {
        return  new User(
                1L,
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                new Date(),
                "rua do teste",
                UserType.CUSTOMER
        );
    }

    public static User createUserOwner() {
        return  new User(
                1L,
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                new Date(),
                "rua do teste",
                UserType.OWNER
        );
    }

    public static User createUserTypeOwner() {
         return new User(
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                new Date(),
                "rua do teste",
                UserType.OWNER
        );
    }

    public static User createUserTypeOwnerWithId() {
        return new User(
                1L,
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                new Date(),
                "rua do teste",
                UserType.OWNER
        );
    }
}
