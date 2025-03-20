package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.IncorrectPasswordException;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.UserGateway;
import java.util.Objects;

public class DeleteUserUseCase {
  private final UserGateway userGateway;

  public DeleteUserUseCase(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  public void execute(UserDeleteDto userDeleteDto) {
    User user =
        userGateway
            .findUserByEmail(userDeleteDto.email())
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    if (!Objects.equals(user.getPassword(), userDeleteDto.password())) {
      throw new IncorrectPasswordException("Senha incorreta, Por favor tente novamente.");
    }
    userGateway.delete(user.getId());
  }
}
