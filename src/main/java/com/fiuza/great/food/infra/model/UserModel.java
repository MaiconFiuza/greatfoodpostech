package com.fiuza.great.food.infra.model;

import com.fiuza.great.food.core.enums.UserType;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String login;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Date lastModification;

  @Column(nullable = false)
  private String address;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private UserType userType;

  @OneToMany(
      mappedBy = "owner",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<RestaurantModel> restaurants = new ArrayList<>();

  public UserModel() {}

  public UserModel(
      Long id,
      String name,
      String email,
      String login,
      String password,
      Date lastModification,
      String address,
      UserType userType,
      List<RestaurantModel> restaurants) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
    this.lastModification = lastModification;
    this.address = address;
    this.userType = userType;
    this.restaurants = restaurants;
  }

  public UserModel(
      Long id,
      String name,
      String email,
      String login,
      String password,
      Date lastModification,
      String address,
      UserType userType) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
    this.lastModification = lastModification;
    this.address = address;
    this.userType = userType;
  }

  public UserModel(
      String name,
      String email,
      String login,
      String password,
      Date lastModification,
      String address,
      UserType userType) {
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
    this.lastModification = lastModification;
    this.address = address;
    this.userType = userType;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public Date getLastModification() {
    return lastModification;
  }

  public String getAddress() {
    return address;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setLastModification(Date lastModification) {
    this.lastModification = lastModification;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public List<RestaurantModel> getRestaurants() {
    return restaurants;
  }

  public void setRestaurants(List<RestaurantModel> restaurants) {
    this.restaurants = restaurants;
  }
}
