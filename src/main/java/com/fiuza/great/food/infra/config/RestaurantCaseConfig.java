package com.fiuza.great.food.infra.config;

import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.core.usecases.restaurant.CreateRestaurantUseCase;
import com.fiuza.great.food.core.usecases.restaurant.DeleteRestaurantUseCase;
import com.fiuza.great.food.core.usecases.restaurant.GetRestaurantByIdUseCase;
import com.fiuza.great.food.core.usecases.restaurant.UpdateRestaurantUseCase;
import com.fiuza.great.food.infra.adapter.repository.RestaurantRepositoryImp;
import com.fiuza.great.food.infra.repository.RestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantCaseConfig {
  @Bean
  public RestaurantGateway restaurantGateway(RestaurantRepository restaurantRepository) {
    return new RestaurantRepositoryImp(restaurantRepository);
  }

  @Bean
  public CreateRestaurantUseCase createRestaurantUseCase(
      RestaurantGateway restaurantGateway, UserGateway userGateway) {
    return new CreateRestaurantUseCase(restaurantGateway, userGateway);
  }

  @Bean
  public GetRestaurantByIdUseCase getRestaurantByIdUseCase(RestaurantGateway restaurantGateway) {
    return new GetRestaurantByIdUseCase(restaurantGateway);
  }

  @Bean
  public UpdateRestaurantUseCase updateRestaurantUseCase(RestaurantGateway restaurantGateway) {
    return new UpdateRestaurantUseCase(restaurantGateway);
  }

  @Bean
  public DeleteRestaurantUseCase deleteRestaurantUseCase(RestaurantGateway restaurantGateway) {
    return new DeleteRestaurantUseCase(restaurantGateway);
  }
}
