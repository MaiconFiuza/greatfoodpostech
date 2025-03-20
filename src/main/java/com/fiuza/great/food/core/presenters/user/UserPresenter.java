package com.fiuza.great.food.core.presenters.user;

import com.fiuza.great.food.core.dto.response.user.UserDtoResponse;
import com.fiuza.great.food.core.dto.response.user.UserRestaurantDtoResponse;
import com.fiuza.great.food.core.entities.user.User;
import java.util.Collections;
import java.util.List;

public class UserPresenter {

  public static UserDtoResponse toUserDtoResponse(User user) {
    if (user.getRestaurants() == null) {
      return new UserDtoResponse(
          user.getId(),
          user.getName(),
          user.getEmail(),
          user.getLogin(),
          user.getLastModification(),
          user.getAddress(),
          Collections.emptyList());
    }

    List<UserRestaurantDtoResponse> restaurants =
        user.getRestaurants().stream()
            .map(r -> new UserRestaurantDtoResponse(r.getName(), r.getOpeningHours()))
            .toList();

    return new UserDtoResponse(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getLogin(),
        user.getLastModification(),
        user.getAddress(),
        restaurants);
  }
}
