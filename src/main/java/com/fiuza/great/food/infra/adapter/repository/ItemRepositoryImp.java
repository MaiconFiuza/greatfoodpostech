package com.fiuza.great.food.infra.adapter.repository;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.ItemGateway;
import com.fiuza.great.food.infra.mappers.ItemsMapper;
import com.fiuza.great.food.infra.mappers.RestaurantMapper;
import com.fiuza.great.food.infra.model.ItemModel;
import com.fiuza.great.food.infra.model.RestaurantModel;
import com.fiuza.great.food.infra.model.UserModel;
import com.fiuza.great.food.infra.repository.ItemRepository;
import java.util.Optional;

public class ItemRepositoryImp implements ItemGateway {

  private final ItemRepository itemRepository;

  public ItemRepositoryImp(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public Item save(Item item) {
    Restaurant restaurant = item.getRestaurant();
    RestaurantModel restaurantModel =
        RestaurantMapper.restaurantToRestaurantModel(item.getRestaurant());

    ItemModel itemModel =
        new ItemModel(
            item.getName(),
            item.getDescription(),
            item.getPrice(),
            item.isAvailableOnDeliveryOption(),
            item.getPic(),
            restaurantModel);

    var itemSaved = itemRepository.save(itemModel);

    return new Item(
        itemSaved.getId(),
        itemSaved.getName(),
        itemSaved.getDescription(),
        itemSaved.getPrice(),
        itemSaved.isAvailableOnDeliveryOption(),
        itemSaved.getPic(),
        restaurant);
  }

  @Override
  public Optional<Item> findItemById(Long id) {
    ItemModel itemModel =
        itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Item não encontrado"));
    RestaurantModel restaurantModel = itemModel.getRestaurant();
    Restaurant restaurant = RestaurantMapper.restaurantModelToRestaurant(restaurantModel);

    return Optional.of(
        new Item(
            itemModel.getId(),
            itemModel.getName(),
            itemModel.getDescription(),
            itemModel.getPrice(),
            itemModel.isAvailableOnDeliveryOption(),
            itemModel.getPic(),
            restaurant));
  }

  @Override
  public Item update(Item item) {
    Restaurant restaurant = item.getRestaurant();
    User user = item.getRestaurant().getOwner();

    UserModel userModel =
        new UserModel(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getLogin(),
            user.getPassword(),
            user.getLastModification(),
            user.getAddress(),
            user.getUserType());
    RestaurantModel restaurantModel =
        new RestaurantModel(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getKitchenType(),
            restaurant.getOpeningHours(),
            userModel);

    ItemModel itemModel =
        new ItemModel(
            item.getId(),
            item.getName(),
            item.getDescription(),
            item.getPrice(),
            item.isAvailableOnDeliveryOption(),
            item.getPic(),
            restaurantModel);

    var itemUpdated = itemRepository.save(itemModel);

    return ItemsMapper.itemModelToItem(itemUpdated);
  }

  @Override
  public void delete(Long id) {
    itemRepository.deleteById(id);
  }
}
