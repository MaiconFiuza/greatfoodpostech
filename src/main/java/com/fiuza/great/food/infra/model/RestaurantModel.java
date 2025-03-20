package com.fiuza.great.food.infra.model;

import com.fiuza.great.food.core.enums.KitchenType;
import jakarta.persistence.*;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class RestaurantModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private KitchenType kitchenType;

  @Column(nullable = false)
  private String openingHours;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel owner;

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ItemModel> items = Collections.emptyList();

  public RestaurantModel() {}

  public RestaurantModel(
      Long id,
      String name,
      KitchenType kitchenType,
      String openingHours,
      UserModel owner,
      List<ItemModel> items) {
    this.id = id;
    this.name = name;
    this.kitchenType = kitchenType;
    this.openingHours = openingHours;
    this.owner = owner;
    this.items = items;
  }

  public RestaurantModel(
      String name, KitchenType kitchenType, String openingHours, UserModel owner) {
    this.name = name;
    this.kitchenType = kitchenType;
    this.openingHours = openingHours;
    this.owner = owner;
  }

  public RestaurantModel(
      Long id, String name, KitchenType kitchenType, String openingHours, UserModel owner) {
    this.id = id;
    this.name = name;
    this.kitchenType = kitchenType;
    this.openingHours = openingHours;
    this.owner = owner;
  }

  public RestaurantModel(Long id, String name, KitchenType kitchenType, String openingHours) {
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

  public UserModel getOwner() {
    return owner;
  }

  public void setOwner(UserModel owner) {
    this.owner = owner;
  }

  public List<ItemModel> getItems() {
    return items;
  }

  public void setItems(List<ItemModel> items) {
    this.items = items;
  }
}
