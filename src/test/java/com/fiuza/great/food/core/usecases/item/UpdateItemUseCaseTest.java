package com.fiuza.great.food.core.usecases.item;

import com.fiuza.great.food.core.dto.request.item.ItemDto;
import com.fiuza.great.food.core.dto.request.item.ItemUpdateDto;
import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.gateway.ItemGateway;
import com.fiuza.great.food.core.gateway.RestaurantGateway;
import com.fiuza.great.food.core.usecases.restaurant.UpdateRestaurantUseCase;
import com.fiuza.great.food.helper.dto.item.ItemDtoHelper;
import com.fiuza.great.food.helper.dto.item.ItemUpdateDtoHelper;
import com.fiuza.great.food.helper.entities.item.ItemHelper;
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
public class UpdateItemUseCaseTest {
    @Mock
    ItemGateway itemGateway;

    @InjectMocks
    UpdateItemUseCase updateItemUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        updateItemUseCase = new UpdateItemUseCase(itemGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void update_item_with_success() {
        // arrange
        ItemUpdateDto itemDto = ItemUpdateDtoHelper.defaultDto();
        Item item = ItemHelper.defaultDto();
        Item itemResult = ItemHelper.moreExpensive();

        when(itemGateway.findItemById(any(Long.class))).thenReturn(Optional.of(item));
        when(itemGateway.update(any(Item.class))).thenReturn(itemResult);


        // act
        var updatedItem = updateItemUseCase.execute(itemDto, 1L);

        // assert
        ArgumentCaptor<Item> userCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemGateway, times(1)).update(userCaptor.capture());

        Item capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(itemDto.name());
        assertThat(capturedUser.getDescription()).isEqualTo(itemDto.description());
        assertThat(updatedItem).isNotNull();
    }
}
