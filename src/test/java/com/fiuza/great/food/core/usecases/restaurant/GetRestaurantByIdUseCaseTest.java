package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantUpdateDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantUpdatDtoHelper;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
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
public class GetRestaurantByIdUseCaseTest {
    @Mock
    RestaurantGateway restaurantGateway;

    @InjectMocks
    GetRestaurantByIdUseCase getRestaurantByIdUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        getRestaurantByIdUseCase = new GetRestaurantByIdUseCase(restaurantGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void get_restaurant_by_id_with_success() {
        // arrange
        Restaurant restaurant = RestaurantHelper.restaurantDefault();

        when(restaurantGateway.findRestaurantById(any(Long.class))).thenReturn(Optional.of(restaurant));

        // act
        var savedRestaurant = getRestaurantByIdUseCase.execute( 1L);

        // assert

        verify(restaurantGateway, times(1)).findRestaurantById(any(Long.class));
        assertThat(savedRestaurant).isNotNull().isEqualTo(restaurant);
    }
}
