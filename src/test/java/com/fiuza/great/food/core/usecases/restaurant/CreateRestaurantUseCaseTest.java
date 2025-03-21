package com.fiuza.great.food.core.usecases.restaurant;

import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.exceptions.WrongTypeOfUserException;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.core.usecases.user.CreateUserUseCase;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantDtoHelper;
import com.fiuza.great.food.helper.dto.user.UserDtoHelper;
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

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRestaurantUseCaseTest {
    @Mock
    RestaurantGateway restaurantGateway;

    @Mock
    UserGateway userGateway;
    @InjectMocks
    CreateRestaurantUseCase createRestaurantUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        createRestaurantUseCase = new CreateRestaurantUseCase(restaurantGateway,userGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_restaurant_with_success() {
        // arrange
        RestaurantDto restaurantDto = RestaurantDtoHelper.defaultDto();
        Restaurant restaurantResult = RestaurantHelper.restaurantDefault();
        User user = UserHelper.createUserOwner();

        when(userGateway.findUserByEmail(restaurantDto.ownerEmail())).thenReturn(Optional.of(user));
        when(restaurantGateway.save(any(Restaurant.class))).thenReturn(restaurantResult);

        // act
        var savedRestaurant = createRestaurantUseCase.execute(restaurantDto);

        // assert
        ArgumentCaptor<Restaurant> userCaptor = ArgumentCaptor.forClass(Restaurant.class);
        verify(restaurantGateway, times(1)).save(userCaptor.capture());

        Restaurant capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(restaurantDto.name());
        assertThat(capturedUser.getOpeningHours()).isEqualTo(restaurantDto.openingHours());
        assertThat(savedRestaurant).isNotNull();
    }

    @Test
    void create_restaurant_should_fail_with_wrong_type_of_user() {
        // arrange
        RestaurantDto restaurantDto = RestaurantDtoHelper.defaultDto();
        User user = UserHelper.createUserWithId();

        when(userGateway.findUserByEmail(restaurantDto.ownerEmail())).thenReturn(Optional.of(user));

        // act
        assertThatThrownBy(
                () -> createRestaurantUseCase.execute(restaurantDto))
                .isInstanceOf(WrongTypeOfUserException.class)
                .hasMessage("Apenas usuários do tipo OWNER podem cadastrar lojas");

        // assert
        verify(userGateway, times(1)).findUserByEmail(any(String.class));
    }
}
