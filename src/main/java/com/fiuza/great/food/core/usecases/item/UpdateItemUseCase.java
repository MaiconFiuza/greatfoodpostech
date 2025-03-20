package com.fiuza.great.food.core.usecases.item;

import com.fiuza.great.food.core.dto.request.item.ItemUpdateDto;
import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.ItemGateway;

public class UpdateItemUseCase {
  private final ItemGateway itemGateway;

  public UpdateItemUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  public Item execute(ItemUpdateDto itemUpdateDto, Long id) {
    var item =
        itemGateway
            .findItemById(id)
            .orElseThrow(() -> new NotFoundException("Item não encontrado"));
    Item updatedItem =
        new Item(
            item.getId(),
            itemUpdateDto.name(),
            itemUpdateDto.description(),
            itemUpdateDto.price(),
            itemUpdateDto.isAvailableOnDeliveryOption(),
            itemUpdateDto.pic(),
            item.getRestaurant());
    return itemGateway.update(updatedItem);
  }
}
