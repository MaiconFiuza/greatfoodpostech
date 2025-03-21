package com.fiuza.great.food.helper.dto.user;

import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.dto.request.user.UserPasswordDto;
import com.fiuza.great.food.core.enums.UserType;

public class UserPasswordDtoHelper {
    public static UserPasswordDto defaultDto() {
        return new UserPasswordDto(
                "velhasenha",
                "novasenha"
        );
    }
}
