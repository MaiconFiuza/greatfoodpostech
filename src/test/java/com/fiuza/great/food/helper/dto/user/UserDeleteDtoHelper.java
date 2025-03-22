package com.fiuza.great.food.helper.dto.user;

import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;

public class UserDeleteDtoHelper {

    public static UserDeleteDto defaultDto() {
        return new UserDeleteDto(
                "teste@teste.com.br",
                "teste123"
        );
    }

    public static UserDeleteDto withWrongPassword() {
        return new UserDeleteDto(
                "teste@teste.com.br",
                "este123"
        );
    }
}
