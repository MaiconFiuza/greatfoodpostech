package com.fiuza.great.food.infra.adapter.repository;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.helper.entities.item.ItemHelper;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import com.fiuza.great.food.helper.model.RestaurantModelHelper;
import com.fiuza.great.food.helper.model.UserModelHelper;
import com.fiuza.great.food.infra.model.ItemModel;
import com.fiuza.great.food.infra.model.RestaurantModel;
import com.fiuza.great.food.infra.model.UserModel;
import com.fiuza.great.food.infra.repository.RestaurantRepository;
import com.fiuza.great.food.infra.repository.UserRepository;
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
public class RestaurantRepositoryImpTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    RestaurantRepositoryImp restaurantRepositoryImp;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        restaurantRepositoryImp = new RestaurantRepositoryImp(restaurantRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void save_success() {
        // arrange
        Restaurant restaurant = RestaurantHelper.restaurantWithoutId();
        RestaurantModel restaurantModel = RestaurantModelHelper.createRestaurantDefault();
        Restaurant restaurantResult = RestaurantHelper.restaurantDefault();

        when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

        // act
        var savedRestaurant= restaurantRepositoryImp.save(restaurant);

        // assert
        ArgumentCaptor<RestaurantModel> userCaptor = ArgumentCaptor.forClass(RestaurantModel.class);
        verify(restaurantRepository, times(1)).save(userCaptor.capture());

        RestaurantModel capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(restaurantResult.getName());
        assertThat(savedRestaurant).isNotNull();
    }

    @Test
    void update_success() {
        // arrange
        Restaurant restaurant = RestaurantHelper.restaurantWithoutId();
        RestaurantModel restaurantModel = RestaurantModelHelper.createRestaurantDefault();
        Restaurant restaurantResult = RestaurantHelper.restaurantDefault();

        when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

        // act
        var savedRestaurant= restaurantRepositoryImp.update(restaurant);

        // assert
        ArgumentCaptor<RestaurantModel> userCaptor = ArgumentCaptor.forClass(RestaurantModel.class);
        verify(restaurantRepository, times(1)).save(userCaptor.capture());

        RestaurantModel capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(restaurantResult.getName());
        assertThat(savedRestaurant).isNotNull();
    }

    @Test
    void find_restaurant_by_id_success() {
        // arrange
        RestaurantModel restaurantModel = RestaurantModelHelper.createRestaurantDefault();
        Restaurant restaurantResult = RestaurantHelper.restaurantDefault();


        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(restaurantModel));

        // act
        restaurantRepositoryImp.findRestaurantById(restaurantResult.getId());

        // assert
        verify(restaurantRepository, times(1)).findById(restaurantResult.getId());
    }

    @Test
    void delete_restaurant_success() {
        // arrange
        Restaurant restaurant = RestaurantHelper.restaurantDefault();

        doNothing().when(restaurantRepository).deleteById(any(Long.class));

        // act
        restaurantRepositoryImp.delete(restaurant.getId());

        // assert
        verify(restaurantRepository, times(1)).deleteById(restaurant.getId());
    }
}
