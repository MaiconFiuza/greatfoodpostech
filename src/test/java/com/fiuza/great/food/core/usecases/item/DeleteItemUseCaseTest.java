package com.fiuza.great.food.core.usecases.item;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.gateway.ItemGateway;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DeleteItemUseCaseTest {
    @Mock
    ItemGateway itemGateway;

    @InjectMocks
    DeleteItemUseCase deleteItemUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        deleteItemUseCase = new DeleteItemUseCase(itemGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void delete_item_with_success() {
        // arrange
        Item itemResult = ItemHelper.defaultDto();

        when(itemGateway.findItemById(any(Long.class))).thenReturn(Optional.of(itemResult));
        doNothing().when(itemGateway).delete(any(Long.class));

        // act
        deleteItemUseCase.execute(1L);

        // assert
        verify(itemGateway, times(1)).delete(any(Long.class));
    }

}
