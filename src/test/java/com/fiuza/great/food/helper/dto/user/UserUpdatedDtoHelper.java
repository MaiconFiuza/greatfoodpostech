package com.fiuza.great.food.helper.dto.user;

import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.dto.request.user.UserUpdateDto;
import com.fiuza.great.food.core.enums.UserType;

public class UserUpdatedDtoHelper {
    public static UserUpdateDto defaultDto() {
        return new UserUpdateDto(
                "Teste",
                "teste@teste.com.br",
                "teste",
                "rua do teste"
        );
    }
}
