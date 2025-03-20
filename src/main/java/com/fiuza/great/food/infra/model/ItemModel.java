package com.fiuza.great.food.infra.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class ItemModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private BigDecimal price;

  private boolean isAvailableOnDeliveryOption;

  @Column(nullable = false)
  private String pic;

  @ManyToOne
  @JoinColumn(name = "restaurant_id", nullable = false)
  private RestaurantModel restaurant;

  public ItemModel() {}

  public ItemModel(
      Long id,
      String name,
      String description,
      BigDecimal price,
      boolean isAvailableOnDeliveryOption,
      String pic,
      RestaurantModel restaurant) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.isAvailableOnDeliveryOption = isAvailableOnDeliveryOption;
    this.pic = pic;
    this.restaurant = restaurant;
  }

  public ItemModel(
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
  }

  public ItemModel(
      String name,
      String description,
      BigDecimal price,
      boolean isAvailableOnDeliveryOption,
      String pic,
      RestaurantModel restaurant) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.isAvailableOnDeliveryOption = isAvailableOnDeliveryOption;
    this.pic = pic;
    this.restaurant = restaurant;
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

  public RestaurantModel getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(RestaurantModel restaurant) {
    this.restaurant = restaurant;
  }
}
