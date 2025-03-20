package com.fiuza.great.food.core.usecases.item;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.ItemGateway;

public class DeleteItemUseCase {
  private final ItemGateway itemGateway;

  public DeleteItemUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  public void execute(Long itemId) {
    Item item =
        itemGateway
            .findItemById(itemId)
            .orElseThrow(() -> new NotFoundException("Item não encontrado"));
    itemGateway.delete(item.getId());
  }
}
