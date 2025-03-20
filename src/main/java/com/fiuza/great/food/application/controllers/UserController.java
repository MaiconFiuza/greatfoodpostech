package com.fiuza.great.food.application.controllers;

import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;
import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.dto.request.user.UserPasswordDto;
import com.fiuza.great.food.core.dto.request.user.UserUpdateDto;
import com.fiuza.great.food.core.dto.response.user.UserDtoResponse;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.presenters.user.UserPresenter;
import com.fiuza.great.food.core.usecases.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Crud para gerenciamento da contas")
public class UserController {
  private static  final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final CreateUserUseCase createUserUseCase;
  private final GetUserByIdUseCase getUserByIdUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final ChangePasswordUseCase changePasswordUseCase;
  private final DeleteUserUseCase deleteUserUseCase;

  public UserController(
      CreateUserUseCase createUserUseCase,
      GetUserByIdUseCase getUserByIdUseCase,
      UpdateUserUseCase updateUserUseCase,
      ChangePasswordUseCase changePasswordUseCase,
      DeleteUserUseCase deleteUserUseCase) {

    this.createUserUseCase = createUserUseCase;
    this.getUserByIdUseCase = getUserByIdUseCase;
    this.updateUserUseCase = updateUserUseCase;
    this.changePasswordUseCase = changePasswordUseCase;
    this.deleteUserUseCase = deleteUserUseCase;
  }

  @Operation(
          description = "Cria a conta do usuário",
          summary = "Endpoint responsável criar a conta de usuário",
          responses = {
                  @ApiResponse(description = "CREATED", responseCode = "201")
          }
  )
  @PostMapping
  public ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDto userDto) {
    logger.info("POST /user");
    User user = createUserUseCase.execute(userDto);
    var result = UserPresenter.toUserDtoResponse(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(
          description = "Busca o cadastro do cliente",
          summary = "Endpoint responsável por retornar o cadastro do usuário",
          responses = {
                  @ApiResponse(description = "OK", responseCode = "200")
          }
  )
  @GetMapping("/{id}")
  public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long id) {
    logger.info("GET /user/"+id);
    User user = getUserByIdUseCase.execute(id);
    var result = UserPresenter.toUserDtoResponse(user);
    return ResponseEntity.ok(result);
  }

  @Operation(
          description = "Atualiza o cadastro do cliente",
          summary = "Endpoint responsável por atualizar o cadastro do usuário",
          responses = {
                  @ApiResponse(description = "OK", responseCode = "200")
          }
  )
  @PutMapping("/{id}")
  public ResponseEntity<UserDtoResponse> updateUser(
      @PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
    logger.info("PUT /user/"+id);
    User user = updateUserUseCase.execute(userUpdateDto, id);
    var result = UserPresenter.toUserDtoResponse(user);
    return ResponseEntity.ok(result);
  }

  @Operation(
          description = "Troca a senha do usuário",
          summary = "Endpoint responsável por trocar a senha do usuário",
          responses = {
                  @ApiResponse(description = "OK", responseCode = "200")
          }
  )
  @PutMapping("/{id}/password")
  public ResponseEntity<UserDtoResponse> updateUserPassword(
      @PathVariable Long id, @RequestBody UserPasswordDto userPasswordDto) {
    logger.info("PUT /user/"+id);
    User user = changePasswordUseCase.execute(id, userPasswordDto);
    var result = UserPresenter.toUserDtoResponse(user);
    return ResponseEntity.ok(result);
  }

  @Operation(
          description = "Exclui o cadastro do cliente",
          summary = "Endpoint responsável pela exclusão do cadastro do usuário",
          responses = {
                  @ApiResponse(description = "NO CONTENT", responseCode = "204")
          }
  )
  @DeleteMapping
  public ResponseEntity<Void> deleteUser(@RequestBody UserDeleteDto userDeleteDto) {
    logger.info("DELETE /user");
    deleteUserUseCase.execute(userDeleteDto);
    return ResponseEntity.noContent().build();
  }
}
