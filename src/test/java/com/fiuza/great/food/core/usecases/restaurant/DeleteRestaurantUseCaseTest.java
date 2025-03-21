package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.helper.dto.user.UserDeleteDtoHelper;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DeleteRestaurantUseCaseTest {
    @Mock
    RestaurantGateway restaurantGateway;

    @InjectMocks
    DeleteRestaurantUseCase deleteRestaurantUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        deleteRestaurantUseCase = new DeleteRestaurantUseCase(restaurantGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void delete_restaurant_with_success() {
        // arrange
        Restaurant restaurantResult = RestaurantHelper.restaurantDefault();

        when(restaurantGateway.findRestaurantById(any(Long.class))).thenReturn(Optional.of(restaurantResult));
        doNothing().when(restaurantGateway).delete(any(Long.class));

        // act
        deleteRestaurantUseCase.execute(1L);

        // assert
        verify(restaurantGateway, times(1)).delete(any(Long.class));
    }

}
