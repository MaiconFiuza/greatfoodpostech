package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserUpdateDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.UserGateway;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UpdateUserUseCase {

  private final UserGateway userGateway;

  public UpdateUserUseCase(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  public User execute(UserUpdateDto userDto, Long id) {
    var user =
        userGateway
            .findUserById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    if (user.getRestaurants().isEmpty()) {
      User updatedUser =
          new User(
              user.getId(),
              userDto.name(),
              userDto.email(),
              userDto.login(),
              user.getPassword(),
              new Date(),
              userDto.address(),
              user.getUserType(),
              Collections.emptyList());

      return userGateway.update(updatedUser);
    }

    List<Restaurant> restaurants =
        user.getRestaurants().stream()
            .map(
                r ->
                    new Restaurant(
                        r.getId(),
                        r.getName(),
                        r.getKitchenType(),
                        r.getOpeningHours(),
                        r.getOwner()))
            .toList();

    User updatedUser =
        new User(
            user.getId(),
            userDto.name(),
            userDto.email(),
            userDto.login(),
            user.getPassword(),
            new Date(),
            userDto.address(),
            user.getUserType(),
            restaurants);

    return userGateway.update(updatedUser);
  }
}
