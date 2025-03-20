package com.fiuza.great.food.core.entities.item;

import com.fiuza.great.food.core.exceptions.NullDataNotNullException;

public class ItemValidation {

  public static void validation(Item item) {
    if (item.getName() == null) {
      throw new NullDataNotNullException("name");
    }
    if (item.getDescription() == null) {
      throw new NullDataNotNullException("description");
    }
    if (item.getPrice() == null) {
      throw new NullDataNotNullException("price");
    }
    if (item.getPic() == null) {
      throw new NullDataNotNullException("pic");
    }
  }
}
