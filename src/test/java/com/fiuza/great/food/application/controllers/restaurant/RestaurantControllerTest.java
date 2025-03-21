package com.fiuza.great.food.application.controllers.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.great.food.application.controllers.RestaurantController;
import com.fiuza.great.food.application.controllers.UserController;
import com.fiuza.great.food.application.handlers.ControllerExceptionHandler;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantUpdateDto;
import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;
import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.dto.request.user.UserUpdateDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.core.usecases.restaurant.CreateRestaurantUseCase;
import com.fiuza.great.food.core.usecases.restaurant.DeleteRestaurantUseCase;
import com.fiuza.great.food.core.usecases.restaurant.GetRestaurantByIdUseCase;
import com.fiuza.great.food.core.usecases.restaurant.UpdateRestaurantUseCase;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantDtoHelper;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantUpdatDtoHelper;
import com.fiuza.great.food.helper.dto.user.UserDeleteDtoHelper;
import com.fiuza.great.food.helper.dto.user.UserUpdatedDtoHelper;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
import com.fiuza.great.food.helper.entities.user.UserHelper;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    CreateRestaurantUseCase createRestaurantUseCase;
    @Mock
    GetRestaurantByIdUseCase getRestaurantByIdUseCase;
    @Mock
    UpdateRestaurantUseCase updateRestaurantUseCase;
    @Mock
    DeleteRestaurantUseCase deleteRestaurantUseCase;

    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    RestaurantController restaurantController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        restaurantController = new RestaurantController(
                createRestaurantUseCase,getRestaurantByIdUseCase,updateRestaurantUseCase,deleteRestaurantUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController)
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
    public void create_restaurant_with_success() throws Exception {
        //arrange
        RestaurantDto restaurantDto = RestaurantDtoHelper.defaultDto();
        Restaurant restaurant = RestaurantHelper.restaurantDefault();

        when(createRestaurantUseCase.execute(any(RestaurantDto.class))).thenReturn(restaurant);

        //act + assert
        mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantDto)))
                .andExpect(status().isCreated());

        verify(createRestaurantUseCase, times(1)).execute(any(RestaurantDto.class));
    }

    @Test
    public void get_restaurant_by_id_with_success() throws Exception {
        //arrange
        Long id = 1L;
        Restaurant restaurant = RestaurantHelper.restaurantDefault();

        when(getRestaurantByIdUseCase.execute(any(Long.class))).thenReturn(restaurant);

        //act & assert
        mockMvc.perform(get("/restaurant/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(getRestaurantByIdUseCase, times(1)).execute(any(Long.class));
    }

    @Test
    public void update_restaurant_by_id_with_success() throws Exception {
        //arrange
        Long id = 1L;
        RestaurantUpdateDto restaurantUpdateDto = RestaurantUpdatDtoHelper.defaultDto();
        Restaurant restaurant = RestaurantHelper.restaurantUpdated();

        when(updateRestaurantUseCase.execute(any(RestaurantUpdateDto.class), eq(id))).thenReturn(restaurant);

        //act & assert
        mockMvc.perform(put("/restaurant/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurantUpdateDto)))
                .andExpect(status().isOk());

        verify(updateRestaurantUseCase, times(1)).execute(any(RestaurantUpdateDto.class), eq(id));
    }

    @Test
    public void delete_user_with_success() throws Exception {
        //arrange
        Long id = 1L;

        doNothing().when(deleteRestaurantUseCase).execute(any(Long.class));

        //act & assert
        mockMvc.perform(delete("/restaurant/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteRestaurantUseCase, times(1)).execute(any(Long.class));
    }
}
