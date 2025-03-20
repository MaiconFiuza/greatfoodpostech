package com.fiuza.great.food.core.entities.item;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import java.math.BigDecimal;

public class Item {
  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private boolean isAvailableOnDeliveryOption;
  private String pic;
  private Restaurant restaurant;

  public Item(
      Long id,
      String name,
      String description,
      BigDecimal price,
      boolean isAvailableOnDeliveryOption,
      String pic,
      Restaurant restaurant) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.isAvailableOnDeliveryOption = isAvailableOnDeliveryOption;
    this.pic = pic;
    this.restaurant = restaurant;
    ItemValidation.validation(this);
  }

  public Item(
      String name,
      String description,
      BigDecimal price,
      boolean isAvailableOnDeliveryOption,
      String pic,
      Restaurant restaurant) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.isAvailableOnDeliveryOption = isAvailableOnDeliveryOption;
    this.pic = pic;
    this.restaurant = restaurant;
    ItemValidation.validation(this);
  }

  public Item(
      Long id,
      String name,
      String description,
      BigDecimal price,
      boolean isAvailableOnDeliveryOption,
      String pic) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.isAvailableOnDeliveryOption = isAvailableOnDeliveryOption;
    this.pic = pic;
    ItemValidation.validation(this);
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public boolean isAvailableOnDeliveryOption() {
    return isAvailableOnDeliveryOption;
  }

  public void setAvailableOnDeliveryOption(boolean availableOnDeliveryOption) {
    isAvailableOnDeliveryOption = availableOnDeliveryOption;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }
}
