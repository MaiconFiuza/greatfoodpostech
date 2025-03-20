package com.fiuza.great.food.application.controllers;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantUpdateDto;
import com.fiuza.great.food.core.dto.response.restaurant.RestaurantDtoResponse;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.presenters.restaurant.RestaurantPresenter;
import com.fiuza.great.food.core.usecases.restaurant.CreateRestaurantUseCase;
import com.fiuza.great.food.core.usecases.restaurant.DeleteRestaurantUseCase;
import com.fiuza.great.food.core.usecases.restaurant.GetRestaurantByIdUseCase;
import com.fiuza.great.food.core.usecases.restaurant.UpdateRestaurantUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
  private static  final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final CreateRestaurantUseCase createRestaurantUseCase;
  private final GetRestaurantByIdUseCase getRestaurantByIdUseCase;
  private final UpdateRestaurantUseCase updateRestaurantUseCase;
  private final DeleteRestaurantUseCase deleteRestaurantUseCase;

  public RestaurantController(
      CreateRestaurantUseCase createRestaurantUseCase,
      GetRestaurantByIdUseCase getRestaurantByIdUseCase,
      UpdateRestaurantUseCase updateRestaurantUseCase,
      DeleteRestaurantUseCase deleteRestaurantUseCase) {
    this.createRestaurantUseCase = createRestaurantUseCase;
    this.getRestaurantByIdUseCase = getRestaurantByIdUseCase;
    this.updateRestaurantUseCase = updateRestaurantUseCase;
    this.deleteRestaurantUseCase = deleteRestaurantUseCase;
  }

  @Operation(
          description = "Cria um restaurante",
          summary = "Endpoint responsável o restaurante",
          responses = {
                  @ApiResponse(description = "CREATED", responseCode = "201")
          }
  )
  @PostMapping
  public ResponseEntity<RestaurantDtoResponse> createRestaurant(
      @RequestBody RestaurantDto restaurantDto) {
    logger.info("POST /restaurant");
    Restaurant restaurant = createRestaurantUseCase.execute(restaurantDto);
    RestaurantDtoResponse result = RestaurantPresenter.toRestaurantDtoResponse(restaurant);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
  @Operation(
          description = "Busca o restaurante",
          summary = "Endpoint responsável por retornar o restaurante",
          responses = {
                  @ApiResponse(description = "OK", responseCode = "200")
          }
  )
  @GetMapping("/{id}")
  public ResponseEntity<RestaurantDtoResponse> getRestaurant(@PathVariable Long id) {
    logger.info("GET /restaurant/"+id);
    Restaurant restaurant = getRestaurantByIdUseCase.execute(id);
    RestaurantDtoResponse result = RestaurantPresenter.toRestaurantDtoResponse(restaurant);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(
          description = "Atualiza o restaurante",
          summary = "Endpoint responsável por atualizar o restaurante",
          responses = {
                  @ApiResponse(description = "OK", responseCode = "200")
          }
  )
  @PutMapping("/{id}")
  public ResponseEntity<RestaurantDtoResponse> updateRestaurant(
      @PathVariable Long id, @RequestBody RestaurantUpdateDto restaurantUpdateDto) {
    logger.info("PUT /restaurant/"+id);
    Restaurant restaurant = updateRestaurantUseCase.execute(restaurantUpdateDto, id);
    RestaurantDtoResponse result = RestaurantPresenter.toRestaurantDtoResponse(restaurant);
    return ResponseEntity.ok(result);
  }

  @Operation(
          description = "Exclui o cadastro do restaurante",
          summary = "Endpoint responsável pela exclusão do cadastro do restaurante",
          responses = {
                  @ApiResponse(description = "NO CONTENT", responseCode = "204")
          }
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
    logger.info("DELETE /restaurant/"+id);
    deleteRestaurantUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}
