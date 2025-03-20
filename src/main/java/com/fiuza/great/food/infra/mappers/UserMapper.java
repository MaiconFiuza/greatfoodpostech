package com.fiuza.great.food.infra.mappers;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.infra.model.RestaurantModel;
import com.fiuza.great.food.infra.model.UserModel;
import java.util.Date;
import java.util.List;

public class UserMapper {
  public static UserModel userToUserModel(User user) {
    if (user.getRestaurants().isEmpty()) {
      return new UserModel(
          user.getId(),
          user.getName(),
          user.getEmail(),
          user.getLogin(),
          user.getPassword(),
          new Date(),
          user.getAddress(),
          user.getUserType());
    }
    List<RestaurantModel> restaurants =
        user.getRestaurants().stream()
            .map(
                r -> {
                  var owner = r.getOwner();
                  var userModel =
                      new UserModel(
                          owner.getId(),
                          owner.getName(),
                          owner.getEmail(),
                          owner.getLogin(),
                          owner.getPassword(),
                          owner.getLastModification(),
                          owner.getAddress(),
                          owner.getUserType());
                  return new RestaurantModel(
                      r.getId(), r.getName(), r.getKitchenType(), r.getOpeningHours(), userModel);
                })
            .toList();

    return new UserModel(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getLogin(),
        user.getPassword(),
        new Date(),
        user.getAddress(),
        user.getUserType(),
        restaurants);
  }

  public static User userModelToUser(UserModel userModel) {
    if (userModel.getRestaurants().isEmpty()) {
      return new User(
          userModel.getId(),
          userModel.getName(),
          userModel.getEmail(),
          userModel.getLogin(),
          userModel.getPassword(),
          new Date(),
          userModel.getAddress(),
          userModel.getUserType());
    }
    List<Restaurant> restaurants =
        userModel.getRestaurants().stream()
            .map(
                r ->
                    new Restaurant(r.getId(), r.getName(), r.getKitchenType(), r.getOpeningHours()))
            .toList();

    return new User(
        userModel.getId(),
        userModel.getName(),
        userModel.getEmail(),
        userModel.getLogin(),
        userModel.getPassword(),
        new Date(),
        userModel.getAddress(),
        userModel.getUserType(),
        restaurants);
  }
}
