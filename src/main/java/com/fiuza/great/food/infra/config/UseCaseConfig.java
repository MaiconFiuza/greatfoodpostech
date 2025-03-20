package com.fiuza.great.food.infra.config;

import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.core.usecases.user.*;
import com.fiuza.great.food.infra.adapter.repository.UserRepositoryImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public CreateUserUseCase createUserUseCase(UserGateway userGateway) {
    return new CreateUserUseCase(userGateway);
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
