package com.fiuza.great.food.core.entities.item;

import com.fiuza.great.food.core.entities.restaurant.Restaurant;
import com.fiuza.great.food.core.exceptions.NullDataNotNullException;
import com.fiuza.great.food.helper.entities.restaurant.RestaurantHelper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ItemValidationTest {

    Restaurant restaurant = RestaurantHelper.restaurantDefault();


    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> new Item(null, "novo", BigDecimal.valueOf(10.0), true, "pic", restaurant))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("name");
    }


    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        assertThatThrownBy(() -> new Item("name", null, BigDecimal.valueOf(10.0), true, "pic", restaurant))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("description");
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        assertThatThrownBy(() -> new Item("name", "desc", null, true, "pic", restaurant))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("price");
    }

    @Test
    void shouldThrowExceptionWhenPicIsNull() {
        assertThatThrownBy(() -> new Item("name", "desc", BigDecimal.valueOf(10.0), true, null,restaurant))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("pic");
    }

    @Test
    void shouldNotThrowExceptionWhenAllFieldsAreValid() {
        Item item = new Item("name", "desc", BigDecimal.valueOf(10.0), true, "pic",restaurant);
        assertThatCode(() -> ItemValidation.validation(item)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotThrowExceptionWhenAllFieldsAreValidWithEmptyConstructor() {
        Item item = new Item();
        item.setId(1L);
        item.setName("teste");
        item.setDescription("Teste");
        item.setPic("teste");
        item.setRestaurant(RestaurantHelper.restaurantDefault());
        item.setPrice(BigDecimal.valueOf(20.00));
        assertThatCode(() -> ItemValidation.validation(item)).doesNotThrowAnyException();
    }
}