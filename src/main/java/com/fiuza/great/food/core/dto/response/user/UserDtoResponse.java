package com.fiuza.great.food.core.dto.response.user;

import java.util.Date;
import java.util.List;

public record UserDtoResponse(
    Long id,
    String name,
    String email,
    String login,
    Date lastModification,
    String address,
    List<UserRestaurantDtoResponse> restaurants) {}
