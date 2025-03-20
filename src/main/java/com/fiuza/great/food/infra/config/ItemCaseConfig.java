package com.fiuza.great.food.infra.config;

import com.fiuza.great.food.core.gateway.ItemGateway;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.core.usecases.item.CreateItemUseCase;
import com.fiuza.great.food.core.usecases.item.DeleteItemUseCase;
import com.fiuza.great.food.core.usecases.item.GetItemByIdUseCase;
import com.fiuza.great.food.core.usecases.item.UpdateItemUseCase;
import com.fiuza.great.food.infra.adapter.repository.ItemRepositoryImp;
import com.fiuza.great.food.infra.repository.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemCaseConfig {

  @Bean
  public ItemGateway itemGateway(ItemRepository itemRepository) {
    return new ItemRepositoryImp(itemRepository);
  }

  @Bean
  public CreateItemUseCase createItemUseCase(
      RestaurantGateway restaurantGateway, ItemGateway itemGateway) {
    return new CreateItemUseCase(restaurantGateway, itemGateway);
  }

  @Bean
  public GetItemByIdUseCase getItemByIdUseCase(ItemGateway itemGateway) {
    return new GetItemByIdUseCase(itemGateway);
  }

  @Bean
  public UpdateItemUseCase updateItemUseCase(ItemGateway itemGateway) {
    return new UpdateItemUseCase(itemGateway);
  }

  @Bean
  public DeleteItemUseCase deleteItemUseCase(ItemGateway itemGateway) {
    return new DeleteItemUseCase(itemGateway);
  }
}
