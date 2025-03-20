package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.UserGateway;

public class GetUserByIdUseCase {

  private final UserGateway userGateway;

  public GetUserByIdUseCase(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  public User execute(Long userId) {
    return userGateway
        .findUserById(userId)
        .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
  }
}
