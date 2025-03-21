package com.fiuza.great.food.application.controllers.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.great.food.application.controllers.ItemController;
import com.fiuza.great.food.application.controllers.RestaurantController;
import com.fiuza.great.food.application.handlers.ControllerExceptionHandler;
import com.fiuza.great.food.core.dto.request.item.ItemDto;
import com.fiuza.great.food.core.dto.request.item.ItemUpdateDto;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantUpdateDto;
import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.usecases.item.CreateItemUseCase;
import com.fiuza.great.food.core.usecases.item.DeleteItemUseCase;
import com.fiuza.great.food.core.usecases.item.GetItemByIdUseCase;
import com.fiuza.great.food.core.usecases.item.UpdateItemUseCase;
import com.fiuza.great.food.helper.dto.item.ItemDtoHelper;
import com.fiuza.great.food.helper.dto.item.ItemUpdateDtoHelper;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantDtoHelper;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantUpdatDtoHelper;
import com.fiuza.great.food.helper.entities.item.ItemHelper;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    CreateItemUseCase createItemUseCase;

    @Mock
    GetItemByIdUseCase getItemByIdUseCase;

    @Mock
    UpdateItemUseCase updateItemUseCase;

    @Mock
    DeleteItemUseCase deleteItemUseCase;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    ItemController itemController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        itemController = new ItemController(
                createItemUseCase,getItemByIdUseCase,updateItemUseCase,deleteItemUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    public void create_item_with_success() throws Exception {
        //arrange
        ItemDto itemDto = ItemDtoHelper.defaultDto();
        Item item = ItemHelper.defaultDto();

        when(createItemUseCase.execute(any(ItemDto.class))).thenReturn(item);

        //act + assert
        mockMvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isCreated());

        verify(createItemUseCase, times(1)).execute(any(ItemDto.class));
    }

    @Test
    public void get_restaurant_by_id_with_success() throws Exception {
        //arrange
        Long id = 1L;
        Item item = ItemHelper.defaultDto();

        when(getItemByIdUseCase.execute(any(Long.class))).thenReturn(item);

        //act & assert
        mockMvc.perform(get("/item/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(getItemByIdUseCase, times(1)).execute(any(Long.class));
    }

    @Test
    public void update_restaurant_by_id_with_success() throws Exception {
        //arrange
        Long id = 1L;
        ItemUpdateDto itemUpdateDto = ItemUpdateDtoHelper.defaultDto();
        Item item = ItemHelper.moreExpensive();

        when(updateItemUseCase.execute(any(ItemUpdateDto.class), eq(id))).thenReturn(item);

        //act & assert
        mockMvc.perform(put("/item/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemUpdateDto)))
                .andExpect(status().isCreated());

        verify(updateItemUseCase, times(1)).execute(any(ItemUpdateDto.class), eq(id));
    }

    @Test
    public void delete_user_with_success() throws Exception {
        //arrange
        Long id = 1L;

        doNothing().when(deleteItemUseCase).execute(any(Long.class));

        //act & assert
        mockMvc.perform(delete("/item/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteItemUseCase, times(1)).execute(any(Long.class));
    }
}
