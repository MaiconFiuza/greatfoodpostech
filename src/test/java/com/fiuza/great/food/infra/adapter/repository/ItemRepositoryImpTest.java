package com.fiuza.great.food.infra.adapter.repository;

import com.fiuza.great.food.core.entities.item.Item;
import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.helper.entities.item.ItemHelper;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
import com.fiuza.great.food.helper.model.ItemModelHelper;
import com.fiuza.great.food.helper.model.RestaurantModelHelper;
import com.fiuza.great.food.infra.model.ItemModel;
import com.fiuza.great.food.infra.model.RestaurantModel;
import com.fiuza.great.food.infra.repository.ItemRepository;
import com.fiuza.great.food.infra.repository.RestaurantRepository;
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
public class ItemRepositoryImpTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    ItemRepositoryImp itemRepositoryImp;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        itemRepositoryImp = new ItemRepositoryImp(itemRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void save_success() {
        // arrange
        Item item = ItemHelper.withouId();
        ItemModel itemModel = ItemModelHelper.createItemDefault();
        Item itemResult = ItemHelper.defaultDto();

        when(itemRepository.save(any(ItemModel.class))).thenReturn(itemModel);

        // act
        var savedItem= itemRepositoryImp.save(item);

        // assert
        ArgumentCaptor<ItemModel> userCaptor = ArgumentCaptor.forClass(ItemModel.class);
        verify(itemRepository, times(1)).save(userCaptor.capture());

        ItemModel capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(itemResult.getName());
        assertThat(savedItem).isNotNull();
    }

    @Test
    void update_success() {
        // arrange
        Item item = ItemHelper.withouId();
        ItemModel itemModel = ItemModelHelper.createItemDefault();
        Item itemResult = ItemHelper.defaultDto();

        when(itemRepository.save(any(ItemModel.class))).thenReturn(itemModel);

        // act
        var savedItem= itemRepositoryImp.update(item);

        // assert
        ArgumentCaptor<ItemModel> userCaptor = ArgumentCaptor.forClass(ItemModel.class);
        verify(itemRepository, times(1)).save(userCaptor.capture());

        ItemModel capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(itemResult.getName());
        assertThat(savedItem).isNotNull();
    }

    @Test
    void find_item_by_id_success() {
        // arrange
        ItemModel itemModel = ItemModelHelper.createItemDefault();
        Item itemResult = ItemHelper.defaultDto();

        when(itemRepository.findById(any(Long.class))).thenReturn(Optional.of(itemModel));

        // act
        itemRepositoryImp.findItemById(itemResult.getId());

        // assert
        verify(itemRepository, times(1)).findById(itemResult.getId());
    }

    @Test
    void delete_item_success() {
        // arrange
        Item itemResult = ItemHelper.defaultDto();

        doNothing().when(itemRepository).deleteById(any(Long.class));

        // act
        itemRepositoryImp.delete(itemResult.getId());

        // assert
        verify(itemRepository, times(1)).deleteById(itemResult.getId());
    }
}
