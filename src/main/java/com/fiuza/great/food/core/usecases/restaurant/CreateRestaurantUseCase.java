package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.enums.UserType;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.exceptions.WrongTypeOfUserException;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.core.gateway.UserGateway;

public class CreateRestaurantUseCase {

  private final RestaurantGateway restaurantGateway;
  private final UserGateway userGateway;

  public CreateRestaurantUseCase(RestaurantGateway restaurantGateway, UserGateway userGateway) {
    this.restaurantGateway = restaurantGateway;
    this.userGateway = userGateway;
  }

  public Restaurant execute(RestaurantDto restaurantDto) {
    User user =
        userGateway
            .findUserByEmail(restaurantDto.ownerEmail())
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    if (user.getUserType() == UserType.CUSTOMER) {
      throw new WrongTypeOfUserException("Apenas usuários do tipo OWNER podem cadastrar lojas");
    }

    Restaurant restaurant =
        new Restaurant(
            restaurantDto.name(), restaurantDto.kitchenType(), restaurantDto.openingHours(), user);

    return restaurantGateway.save(restaurant);
  }
}
