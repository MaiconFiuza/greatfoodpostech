package com.fiuza.great.food.infra.adapter.repository;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.infra.mappers.UserMapper;
import com.fiuza.great.food.infra.model.UserModel;
import com.fiuza.great.food.infra.repository.UserRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserGateway {
  private final UserRepository userRepository;

  public UserRepositoryImp(com.fiuza.great.food.infra.repository.UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User save(User user) {
    UserModel newUser =
        new UserModel(
            user.getName(),
            user.getEmail(),
            user.getLogin(),
            user.getPassword(),
            new Date(),
            user.getAddress(),
            user.getUserType());

    var userSaved = userRepository.save(newUser);

    return new User(
        userSaved.getId(),
        userSaved.getName(),
        userSaved.getEmail(),
        userSaved.getLogin(),
        userSaved.getPassword(),
        userSaved.getLastModification(),
        userSaved.getAddress(),
        userSaved.getUserType(),
        null);
  }

  @Override
  public Optional<User> findUserById(Long id) {
    UserModel userSaved =
        userRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    if (userSaved.getRestaurants().isEmpty()) {
      return Optional.of(
          new User(
              userSaved.getId(),
              userSaved.getName(),
              userSaved.getEmail(),
              userSaved.getLogin(),
              userSaved.getPassword(),
              userSaved.getLastModification(),
              userSaved.getAddress(),
              userSaved.getUserType()));
    }

    List<Restaurant> restaurants =
        userSaved.getRestaurants().stream()
            .map(
                r -> {
                  var owner = r.getOwner();
                  var user =
                      new User(
                          owner.getId(),
                          owner.getName(),
                          owner.getEmail(),
                          owner.getLogin(),
                          owner.getPassword(),
                          owner.getLastModification(),
                          owner.getAddress(),
                          owner.getUserType());
                  return new Restaurant(
                      r.getId(),
                      r.getName(),
                      r.getKitchenType(),
                      r.getOpeningHours(),
                      user); // 🚀 Agora retorna corretamente
                })
            .collect(Collectors.toList());

    return Optional.of(
        new User(
            userSaved.getId(),
            userSaved.getName(),
            userSaved.getEmail(),
            userSaved.getLogin(),
            userSaved.getPassword(),
            userSaved.getLastModification(),
            userSaved.getAddress(),
            userSaved.getUserType(),
            restaurants));
  }

  @Override
  public Optional<User> findUserByEmail(String email) {
    UserModel userSaved =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    if (userSaved.getRestaurants().isEmpty()) {
      return Optional.of(
          new User(
              userSaved.getId(),
              userSaved.getName(),
              userSaved.getEmail(),
              userSaved.getLogin(),
              userSaved.getPassword(),
              userSaved.getLastModification(),
              userSaved.getAddress(),
              userSaved.getUserType(),
              Collections.emptyList()));
    }
    List<Restaurant> restaurants = new ArrayList<>();

    for (var i = 0; i < userSaved.getRestaurants().size(); i++) {
      var restaurant =
          new Restaurant(
              userSaved.getRestaurants().get(i).getId(),
              userSaved.getRestaurants().get(i).getName(),
              userSaved.getRestaurants().get(i).getKitchenType(),
              userSaved.getRestaurants().get(i).getOpeningHours());
      restaurants.add(restaurant);
    }

    return Optional.of(
        new User(
            userSaved.getId(),
            userSaved.getName(),
            userSaved.getEmail(),
            userSaved.getLogin(),
            userSaved.getPassword(),
            userSaved.getLastModification(),
            userSaved.getAddress(),
            userSaved.getUserType(),
            restaurants));
  }

  @Override
  public boolean findUserByEmailValidation(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  @Override
  public User update(User user) {
    UserModel userModel = UserMapper.userToUserModel(user);
    var userUpdate = userRepository.save(userModel);

    return UserMapper.userModelToUser(userUpdate);
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
  }
}
