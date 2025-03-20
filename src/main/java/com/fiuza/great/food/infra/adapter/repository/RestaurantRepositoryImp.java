package com.fiuza.great.food.infra.adapter.repository;

import static java.util.Collections.emptyList;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.infra.mappers.RestaurantMapper;
import com.fiuza.great.food.infra.model.RestaurantModel;
import com.fiuza.great.food.infra.model.UserModel;
import com.fiuza.great.food.infra.repository.RestaurantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImp implements RestaurantGateway {

  private final RestaurantRepository restaurantRepository;

  public RestaurantRepositoryImp(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  @Override
  public Restaurant save(Restaurant restaurant) {
    UserModel userModel =
        new UserModel(
            restaurant.getOwner().getId(),
            restaurant.getOwner().getName(),
            restaurant.getOwner().getEmail(),
            restaurant.getOwner().getLogin(),
            restaurant.getOwner().getPassword(),
            restaurant.getOwner().getLastModification(),
            restaurant.getOwner().getAddress(),
            restaurant.getOwner().getUserType(),
            emptyList());
    List<RestaurantModel> restaurantsModels = new ArrayList<>();
    RestaurantModel restaurantModel =
        new RestaurantModel(
            restaurant.getName(),
            restaurant.getKitchenType(),
            restaurant.getOpeningHours(),
            userModel);
    restaurantsModels.add(restaurantModel);

    restaurantModel.getOwner().setRestaurants(restaurantsModels);

    var restaurantSaved = restaurantRepository.save(restaurantModel);
    return new Restaurant(
        restaurantSaved.getId(),
        restaurantSaved.getName(),
        restaurantSaved.getKitchenType(),
        restaurantSaved.getOpeningHours(),
        restaurant.getOwner());
  }

  @Override
  public Optional<Restaurant> findRestaurantById(Long id) {
    var restaurant =
        restaurantRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Restaurante não encontrado"));

    UserModel userModel = restaurant.getOwner();
    User user =
        new User(
            userModel.getId(),
            userModel.getName(),
            userModel.getEmail(),
            userModel.getLogin(),
            userModel.getPassword(),
            userModel.getLastModification(),
            userModel.getAddress(),
            userModel.getUserType());

    List<Item> items = restaurant.getItems().stream()
            .map(i -> {
                return new Item(
                     i.getId(),
                     i.getName(),
                     i.getDescription(),
                     i.getPrice(),
                     i.isAvailableOnDeliveryOption(),
                     i.getPic()
             );
            }).toList();

    return Optional.of(
        new Restaurant(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getKitchenType(),
            restaurant.getOpeningHours(),
            user,
            items
        ));
  }

  @Override
  public Restaurant update(Restaurant restaurant) {
    RestaurantModel restaurantModel = RestaurantMapper.restaurantToRestaurantModel(restaurant);
    var restaurantUpdated = restaurantRepository.save(restaurantModel);
    return RestaurantMapper.restaurantModelToRestaurant(restaurantUpdated);
  }

  @Override
  public void delete(Long id) {
    restaurantRepository.deleteById(id);
  }
}
