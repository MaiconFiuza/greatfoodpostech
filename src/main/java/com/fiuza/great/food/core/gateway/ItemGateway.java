package com.fiuza.great.food.core.gateway;

import com.fiuza.great.food.core.entities.item.Item;
import java.util.Optional;

public interface ItemGateway {
  Item save(Item item);

  Optional<Item> findItemById(Long id);

  Item update(Item item);

  void delete(Long id);
}
