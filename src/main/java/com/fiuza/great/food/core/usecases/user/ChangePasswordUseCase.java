package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserPasswordDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.IncorrectPasswordException;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.UserGateway;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class ChangePasswordUseCase {
  private final UserGateway userGateway;
  private final Clock clock;

  public ChangePasswordUseCase(UserGateway userGateway, Clock clock) {
    this.userGateway = userGateway;
    this.clock = clock;
  }

  public User execute(Long id, UserPasswordDto userPasswordDto) {
    var user =
        userGateway
            .findUserById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    if (!Objects.equals(user.getPassword(), userPasswordDto.oldPassword())) {
      throw new IncorrectPasswordException("Senha incorreta");
    }

    User updateUser =
        new User(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getLogin(),
            userPasswordDto.newPassword(),
            Date.from(Instant.now(clock)),
            user.getAddress(),
            user.getUserType(),
            user.getRestaurants());

    return userGateway.update(updateUser);
  }
}
