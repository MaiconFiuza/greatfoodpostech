package com.fiuza.great.food.infra.mappers;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.infra.model.ItemModel;

public class ItemsMapper {

  public static ItemModel itemToItemModel(Item item) {

    return new ItemModel(
        item.getId(),
        item.getName(),
        item.getDescription(),
        item.getPrice(),
        item.isAvailableOnDeliveryOption(),
        item.getPic());
  }

  public static Item itemModelToItem(ItemModel itemModel) {
    Restaurant restaurant = RestaurantMapper.restaurantModelToRestaurant(itemModel.getRestaurant());
    return new Item(
        itemModel.getId(),
        itemModel.getName(),
        itemModel.getDescription(),
        itemModel.getPrice(),
        itemModel.isAvailableOnDeliveryOption(),
        itemModel.getPic(),
        restaurant);
  }
}
