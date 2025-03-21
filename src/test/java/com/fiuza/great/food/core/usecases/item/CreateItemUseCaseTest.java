package com.fiuza.great.food.core.usecases.item;

import com.fiuza.great.food.core.dto.request.item.ItemDto;
import com.fiuza.great.food.core.dto.request.restaurant.RestaurantDto;
import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.gateway.ItemGateway;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.core.usecases.restaurant.CreateRestaurantUseCase;
import com.fiuza.great.food.helper.dto.item.ItemDtoHelper;
import com.fiuza.great.food.helper.dto.restaurant.RestaurantDtoHelper;
import com.fiuza.great.food.helper.entities.item.ItemHelper;
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
public class CreateItemUseCaseTest {
    @Mock
    RestaurantGateway restaurantGateway;
    @Mock
    ItemGateway itemGateway;

    @InjectMocks
    CreateItemUseCase createItemUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        createItemUseCase = new CreateItemUseCase(restaurantGateway,itemGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_item_with_success() {
        // arrange
        ItemDto itemDto = ItemDtoHelper.defaultDto();
        Item itemResult = ItemHelper.defaultDto();
        Restaurant restaurant = RestaurantHelper.restaurantDefault();

        when(restaurantGateway.findRestaurantById(any(Long.class))).thenReturn(Optional.of(restaurant));
        when(itemGateway.save(any(Item.class))).thenReturn(itemResult);


        // act
        var savedItem = createItemUseCase.execute(itemDto);

        // assert
        ArgumentCaptor<Item> userCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemGateway, times(1)).save(userCaptor.capture());

        Item capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(itemDto.name());
        assertThat(capturedUser.getDescription()).isEqualTo(itemDto.description());
        assertThat(savedItem).isNotNull();
    }
}
