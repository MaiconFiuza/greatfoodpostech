package com.fiuza.great.food.infra.config;

import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.core.usecases.user.*;
import com.fiuza.great.food.infra.adapter.repository.UserRepositoryImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class UseCaseConfig {
  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }

  @Bean
  public CreateUserUseCase createUserUseCase(UserGateway userGateway, Clock clock) {
    return new CreateUserUseCase(userGateway,clock);
  }

  @Bean
  public GetUserByIdUseCase getUserByIdUseCase(UserGateway userGateway) {
    return new GetUserByIdUseCase(userGateway);
  }

  @Bean
  public UpdateUserUseCase updateUserUseCase(UserGateway userGateway) {
    return new UpdateUserUseCase(userGateway);
  }

  @Bean
  public ChangePasswordUseCase changePasswordUseCase(UserGateway userGateway) {
    return new ChangePasswordUseCase(userGateway);
  }

  @Bean
  public DeleteUserUseCase deleteUserUseCase(UserGateway userGateway) {
    return new DeleteUserUseCase(userGateway);
  }

  @Bean
  public UserGateway userGateway(
      com.fiuza.great.food.infra.repository.UserRepository userRepository) {
    return new UserRepositoryImp(userRepository);
  }
}
