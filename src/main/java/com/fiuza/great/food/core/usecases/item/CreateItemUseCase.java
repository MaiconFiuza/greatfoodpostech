package com.fiuza.great.food.core.usecases.item;

import com.fiuza.great.food.core.dto.request.item.ItemDto;
import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.ItemGateway;
import com.fiuza.great.food.core.gateway.RestaurantGateway;

public class CreateItemUseCase {

  private final RestaurantGateway restaurantGateway;
  private final ItemGateway itemGateway;

  public CreateItemUseCase(RestaurantGateway restaurantGateway, ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
    this.restaurantGateway = restaurantGateway;
  }

  public Item execute(ItemDto itemDto) {
    Restaurant restaurant =
        restaurantGateway
            .findRestaurantById(itemDto.restaurantId())
            .orElseThrow(() -> new NotFoundException("Restaurante não encontrado"));
    Item item =
        new Item(
            itemDto.name(),
            itemDto.description(),
            itemDto.price(),
            itemDto.isAvailableOnDeliveryOption(),
            itemDto.pic(),
            restaurant);
    return itemGateway.save(item);
  }
}
