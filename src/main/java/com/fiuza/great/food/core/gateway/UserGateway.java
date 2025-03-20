package com.fiuza.great.food.core.gateway;

import com.fiuza.great.food.core.entities.user.User;
import java.util.Optional;

public interface UserGateway {
  User save(User user);

  Optional<User> findUserById(Long id);

  Optional<User> findUserByEmail(String email);

  boolean findUserByEmailValidation(String email);

  User update(User user);

  void delete(Long id);
}
