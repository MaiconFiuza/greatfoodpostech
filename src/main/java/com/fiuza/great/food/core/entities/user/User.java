package com.fiuza.great.food.core.entities.user;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.enums.UserType;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User {
  private Long id;
  private String name;
  private String email;
  private String login;
  private String password;
  private Date lastModification;
  private String address;
  private UserType userType;
  private List<Restaurant> restaurants = Collections.emptyList();

  public User(
      Long id,
      String name,
      String email,
      String login,
      String password,
      Date lastModification,
      String address,
      UserType userType,
      List<Restaurant> restaurants) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
    this.lastModification = lastModification;
    this.address = address;
    this.userType = userType;
    this.restaurants = restaurants;
    UserValidation.validation(this);
  }

  public User(
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
    UserValidation.validation(this);
  }

  public User(
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
    UserValidation.validation(this);
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

  public List<Restaurant> getRestaurants() {
    return restaurants;
  }

  public void setRestaurant(List<Restaurant> restaurants) {
    this.restaurants = restaurants;
  }
}
