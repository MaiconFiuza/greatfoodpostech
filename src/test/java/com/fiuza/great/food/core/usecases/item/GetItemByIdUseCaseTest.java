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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetItemByIdUseCaseTest {
    @Mock
    ItemGateway itemGateway;

    @InjectMocks
    GetItemByIdUseCase getItemByIdUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        getItemByIdUseCase = new GetItemByIdUseCase(itemGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void get_item_by_id_with_success() {
        // arrange
        Item item = ItemHelper.defaultDto();

        when(itemGateway.findItemById(any(Long.class))).thenReturn(Optional.of(item));

        // act
        var savedItem = getItemByIdUseCase.execute( 1L);

        // assert

        verify(itemGateway, times(1)).findItemById(any(Long.class));
        assertThat(savedItem).isNotNull().isEqualTo(item);

    }
}
