package com.fiuza.great.food.core.dto;

import com.fiuza.great.food.core.dto.response.item.ItemDtoResponse;
import com.fiuza.great.food.core.dto.response.item.RestaurantItemDtoReponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemRestaurantDtoResponseTest {
    @Test
    void shouldCreateItemDtoResponseSuccessfully() {
        // Arrange
        Long id = 1L;
        String name = "Pizza";
        String description = "Delicious cheese pizza";
        BigDecimal price = new BigDecimal("29.99");
        boolean isAvailableOnDeliveryOption = true;
        String pic = "image_url";
        RestaurantItemDtoReponse restaurant = new RestaurantItemDtoReponse(10L, "Pizzeria");

        // Act
        ItemDtoResponse itemDto = new ItemDtoResponse(id, name, description, price, isAvailableOnDeliveryOption, pic, restaurant);

        // Assert
        assertThat(itemDto).isNotNull();
        assertThat(itemDto.id()).isEqualTo(id);
        assertThat(itemDto.name()).isEqualTo(name);
        assertThat(itemDto.description()).isEqualTo(description);
        assertThat(itemDto.price()).isEqualByComparingTo(price);
        assertThat(itemDto.isAvailableOnDeliveryOption()).isTrue();
        assertThat(itemDto.pic()).isEqualTo(pic);
        assertThat(itemDto.restaurant()).isEqualTo(restaurant);
    }
}
