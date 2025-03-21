package com.fiuza.great.food.helper.dto.user;

import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.enums.UserType;

public class UserDtoHelper {

    public static UserDto defaultDto() {
        return new UserDto(
                "Teste",
                "teste@teste.com.br",
                "teste",
                "teste123",
                "rua do teste",
                UserType.CUSTOMER
        );
    }

    public static UserDto withNull() {
        return new UserDto(
                "Teste",
                null,
                "teste",
                "teste123",
                "rua do teste",
                UserType.CUSTOMER
        );
    }
}
