package com.fiuza.great.food.infra.mappers;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.infra.model.ItemModel;
import com.fiuza.great.food.infra.model.RestaurantModel;
import com.fiuza.great.food.infra.model.UserModel;
import java.util.List;

public class RestaurantMapper {
  public static RestaurantModel restaurantToRestaurantModel(Restaurant restaurant) {
    UserModel userModel = UserMapper.userToUserModel(restaurant.getOwner());
    List<ItemModel> items =
        restaurant.getItem().stream()
            .map(
                i ->
                    new ItemModel(
                        i.getId(),
                        i.getName(),
                        i.getDescription(),
                        i.getPrice(),
                        i.isAvailableOnDeliveryOption(),
                        i.getPic()))
            .toList();
    return new RestaurantModel(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getKitchenType(),
        restaurant.getOpeningHours(),
        userModel,
        items);
  }

  public static Restaurant restaurantModelToRestaurant(RestaurantModel restaurantModel) {
    User user = UserMapper.userModelToUser(restaurantModel.getOwner());

    List<Item> items =
        restaurantModel.getItems().stream()
            .map(
                i ->
                    new Item(
                        i.getId(),
                        i.getName(),
                        i.getDescription(),
                        i.getPrice(),
                        i.isAvailableOnDeliveryOption(),
                        i.getPic()))
            .toList();

    return new Restaurant(
        restaurantModel.getId(),
        restaurantModel.getName(),
        restaurantModel.getKitchenType(),
        restaurantModel.getOpeningHours(),
        user,
        items);
  }
}
