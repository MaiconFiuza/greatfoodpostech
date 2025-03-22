package com.fiuza.great.food.core.entities.restaurant;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.enums.KitchenType;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {

  private Long id;
  private String name;

  private KitchenType kitchenType;

  private String openingHours;

  private User owner;

  private List<Item> items = Collections.emptyList();

  public Restaurant(){};
  public Restaurant(
      Long id, String name, KitchenType kitchenType, String openingHours, User owner) {
    this.id = id;
    this.name = name;
    this.kitchenType = kitchenType;
    this.openingHours = openingHours;
    this.owner = owner;
    RestaurantValidation.validation(this);
  }

  public Restaurant(
      Long id,
      String name,
      KitchenType kitchenType,
      String openingHours,
      User owner,
      List<Item> item) {
    this.id = id;
    this.name = name;
    this.kitchenType = kitchenType;
    this.openingHours = openingHours;
    this.owner = owner;
    this.items = item;
    RestaurantValidation.validation(this);
  }

  public Restaurant(String name, KitchenType kitchenType, String openingHours, User owner) {
    this.name = name;
    this.kitchenType = kitchenType;
    this.openingHours = openingHours;
    this.owner = owner;
    RestaurantValidation.validation(this);
  }

  public Restaurant(Long id, String name, KitchenType kitchenType, String openingHours) {
    this.id = id;
    this.name = name;
    this.kitchenType = kitchenType;
    this.openingHours = openingHours;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public KitchenType getKitchenType() {
    return kitchenType;
  }

  public void setKitchenType(KitchenType kitchenType) {
    this.kitchenType = kitchenType;
  }

  public String getOpeningHours() {
    return openingHours;
  }

  public void setOpeningHours(String openingHours) {
    this.openingHours = openingHours;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public List<Item> getItem() {
    return items;
  }

  public void setItem(List<Item> items) {
    this.items = items;
  }
}
