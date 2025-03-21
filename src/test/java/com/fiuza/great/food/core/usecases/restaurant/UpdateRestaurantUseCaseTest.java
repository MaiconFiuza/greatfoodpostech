package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantUpdateDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantDtoHelper;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantUpdatDtoHelper;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateRestaurantUseCaseTest {
    @Mock
    RestaurantGateway restaurantGateway;

    @InjectMocks
    UpdateRestaurantUseCase updateRestaurantUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        updateRestaurantUseCase = new UpdateRestaurantUseCase(restaurantGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_restaurant_with_success() {
        // arrange
        RestaurantUpdateDto restaurantUpdateDto = RestaurantUpdatDtoHelper.defaultDto();
        Restaurant restaurant = RestaurantHelper.restaurantDefault();
        Restaurant restaurantResult = RestaurantHelper.restaurantUpdated();

        when(restaurantGateway.findRestaurantById(any(Long.class))).thenReturn(Optional.of(restaurant));
        when(restaurantGateway.update(any(Restaurant.class))).thenReturn(restaurantResult);

        // act
        var savedRestaurant = updateRestaurantUseCase.execute(restaurantUpdateDto, 1L);

        // assert
        ArgumentCaptor<Restaurant> userCaptor = ArgumentCaptor.forClass(Restaurant.class);
        verify(restaurantGateway, times(1)).update(userCaptor.capture());

        Restaurant capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(restaurantUpdateDto.name());
        assertThat(capturedUser.getOpeningHours()).isEqualTo(restaurantUpdateDto.openingHours());
        assertThat(savedRestaurant).isNotNull();
    }
}
