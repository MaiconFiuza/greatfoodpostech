package com.fiuza.great.food.core.entities.restaurant;

import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.enums.KitchenType;
import com.fiuza.great.food.core.exceptions.NullDataNotNullException;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RestaurantValidationTest {

    User user = UserHelper.createUserWithId();

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> new Restaurant(1L, null, KitchenType.NORDESTINA,
                "das 10 -18", user))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("name");
    }


    @Test
    void shouldThrowExceptionWhenKitchenTypeIsNull() {
        assertThatThrownBy(() -> new Restaurant(1L, "desc", null,
                "das 10 -18", user))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("kitchenType");
    }

    @Test
    void shouldThrowExceptionWhenOwnerIsNull() {
        assertThatThrownBy(() -> new Restaurant(1L, "desc", KitchenType.NORDESTINA,
                "das 10 -18", null))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("owner");
    }

    @Test
    void shouldThrowExceptionWhenOpeningHoursIsNull() {
        assertThatThrownBy(() -> new Restaurant(1L, "desc", KitchenType.NORDESTINA,
                null, user))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("openingHours");
    }

    @Test
    void shouldNotThrowExceptionWhenAllFieldsAreValid() {
        Restaurant restaurant = new Restaurant(1L, "desc", KitchenType.NORDESTINA,
                "das 10 -18", user);
        assertThatCode(() -> RestaurantValidation.validation(restaurant)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotThrowExceptionWhenAllFieldsAreValidWithEmptyConstructor() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("teste");
        restaurant.setKitchenType(KitchenType.NORDESTINA);
        restaurant.setOpeningHours("das 10 -18");
        restaurant.setOwner(user);
        assertThatCode(() -> RestaurantValidation.validation(restaurant)).doesNotThrowAnyException();
    }
}
