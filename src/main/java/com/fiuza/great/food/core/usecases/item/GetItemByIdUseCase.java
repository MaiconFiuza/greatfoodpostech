package com.fiuza.great.food.core.usecases.item;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.ItemGateway;

public class GetItemByIdUseCase {
  private final ItemGateway itemGateway;

  public GetItemByIdUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  public Item execute(Long itemId) {
    return itemGateway
        .findItemById(itemId)
        .orElseThrow(() -> new NotFoundException("Item não encontrado"));
  }
}
