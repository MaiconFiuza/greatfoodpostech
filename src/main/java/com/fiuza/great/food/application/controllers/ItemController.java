package com.fiuza.great.food.application.controllers;

import com.fiuza.great.food.core.dto.request.item.ItemDto;
import com.fiuza.great.food.core.dto.request.item.ItemUpdateDto;
import com.fiuza.great.food.core.dto.response.item.ItemDtoResponse;
import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.presenters.item.ItemPresenter;
import com.fiuza.great.food.core.usecases.item.CreateItemUseCase;
import com.fiuza.great.food.core.usecases.item.DeleteItemUseCase;
import com.fiuza.great.food.core.usecases.item.GetItemByIdUseCase;
import com.fiuza.great.food.core.usecases.item.UpdateItemUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
  private static  final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final CreateItemUseCase createItemUseCase;
  private final GetItemByIdUseCase getItemByIdUseCase;
  private final UpdateItemUseCase updateItemUseCase;
  private final DeleteItemUseCase deleteItemUseCase;

  public ItemController(
      CreateItemUseCase createItemUseCase,
      GetItemByIdUseCase getItemByIdUseCase,
      UpdateItemUseCase updateItemUseCase,
      DeleteItemUseCase deleteItemUseCase) {
    this.createItemUseCase = createItemUseCase;
    this.getItemByIdUseCase = getItemByIdUseCase;
    this.updateItemUseCase = updateItemUseCase;
    this.deleteItemUseCase = deleteItemUseCase;
  }

  @Operation(
          description = "Cria um item",
          summary = "Endpoint responsável o item",
          responses = {
                  @ApiResponse(description = "CREATED", responseCode = "201")
          }
  )
  @PostMapping
  public ResponseEntity<ItemDtoResponse> createItem(@RequestBody ItemDto itemDto) throws Exception {
    logger.info("POST /item");
    Item item = createItemUseCase.execute(itemDto);
    ItemDtoResponse result = ItemPresenter.toItemDtoResponse(item);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(
          description = "Busca o item",
          summary = "Endpoint responsável por retornar o item",
          responses = {
                  @ApiResponse(description = "OK", responseCode = "200")
          }
  )
  @GetMapping("/{id}")
  public ResponseEntity<ItemDtoResponse> getItemById(@PathVariable Long id) {
    logger.info("GET /item/"+id);
    Item item = getItemByIdUseCase.execute(id);
    ItemDtoResponse result = ItemPresenter.toItemDtoResponse(item);
    return ResponseEntity.ok(result);
  }

  @Operation(
          description = "Atualiza o item",
          summary = "Endpoint responsável por atualizar o item",
          responses = {
                  @ApiResponse(description = "OK", responseCode = "200")
          }
  )
  @PutMapping("/{id}")
  public ResponseEntity<ItemDtoResponse> updateItemById(
      @PathVariable Long id, @RequestBody ItemUpdateDto itemUpdateDto) {
    logger.info("PUT /item/"+id);
    Item item = updateItemUseCase.execute(itemUpdateDto, id);
    ItemDtoResponse result = ItemPresenter.toItemDtoResponse(item);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(
          description = "Exclui o cadastro do item",
          summary = "Endpoint responsável pela exclusão do cadastro do item",
          responses = {
                  @ApiResponse(description = "NO CONTENT", responseCode = "204")
          }
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
    logger.info("DELETE /item/"+id);
    deleteItemUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}
