package com.fiuza.great.food.core.entities.user;

import com.fiuza.great.food.core.exceptions.NullDataNotNullException;

public class UserValidation {

  public static void validation(User user) {
    if (user.getName() == null) {
      throw new NullDataNotNullException("name");
    }
    if (user.getEmail() == null) {
      throw new NullDataNotNullException("email");
    }
    if (user.getLogin() == null) {
      throw new NullDataNotNullException("login");
    }
    if (user.getPassword() == null) {
      throw new NullDataNotNullException("password");
    }
    if (user.getAddress() == null) {
      throw new NullDataNotNullException("address");
    }
    if (user.getUserType() == null) {
      throw new NullDataNotNullException("userType");
    }
  }
}
