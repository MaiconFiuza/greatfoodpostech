package com.fiuza.great.food.core.dto.request.user;

import com.fiuza.great.food.core.enums.UserType;

public record UserDto(
    String name, String email, String login, String password, String address, UserType userType) {}
