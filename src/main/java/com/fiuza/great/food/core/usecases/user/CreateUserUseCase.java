package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.CannotUseSameEmailException;
import com.fiuza.great.food.core.exceptions.InternalServerError;
import com.fiuza.great.food.core.exceptions.NullDataNotNullException;
import com.fiuza.great.food.core.gateway.UserGateway;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

public class CreateUserUseCase {

  private final UserGateway userGateway;
  private final Clock clock;

  public CreateUserUseCase(UserGateway userGateway, Clock clock) {
    this.userGateway = userGateway;
    this.clock = clock;
  }

  public User execute(UserDto userDto) {
    try {
      var hasUserEmail = userGateway.findUserByEmailValidation(userDto.email());
      if (hasUserEmail) {
        throw new CannotUseSameEmailException("O email indicado já está em uso");
      }
      User user =
          new User(
              userDto.name(),
              userDto.email(),
              userDto.login(),
              userDto.password(),
              Date.from(Instant.now(clock)),
              userDto.address(),
              userDto.userType());
      return userGateway.save(user);

    } catch (NullDataNotNullException e) {
      throw new NullDataNotNullException("Todos os campos devem ser enviados");
    } catch (CannotUseSameEmailException e) {
      throw new CannotUseSameEmailException(e.getMessage());
    } catch (Exception e) {
      throw new InternalServerError(e.getMessage());
    }
  }
}
