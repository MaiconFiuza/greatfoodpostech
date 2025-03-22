package com.fiuza.great.food.core.entities.user;

import com.fiuza.great.food.core.enums.UserType;
import com.fiuza.great.food.core.exceptions.NullDataNotNullException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserValidationTest {

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThatThrownBy(() -> new User(null, "testeFifiuza", "fifiuza",
                "senha123", new Date(), "endereco",UserType.CUSTOMER))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("name");
    }

    @Test
    void shouldThrowExceptionWhenLoginIsNull() {
        assertThatThrownBy(() -> new User("fifiuza", "testeFifiuza", null,
                "senha123", new Date(), "endereco",UserType.CUSTOMER))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("login");
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        assertThatThrownBy(() -> new User("fifiuza", "testeFifiuza", "fifiuza",
                null, new Date(), "endereco",UserType.CUSTOMER))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("password");
    }

    @Test
    void shouldThrowExceptionWhenAddressIsNull() {
        assertThatThrownBy(() -> new User("fifiuza", "testeFifiuza", "fifiuza",
                "senha123", new Date(), null,UserType.CUSTOMER))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("address");
    }

    @Test
    void shouldThrowExceptionWhenUserTypeIsNull() {
        assertThatThrownBy(() -> new User("fifiuza", "testeFifiuza", "fifiuza",
                "senha123", new Date(), "endereco",null))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("userType");
    }


    @Test
    void shouldNotThrowExceptionWhenAllFieldsAreValid() {
        User user = new User("fifiuza", "testeFifiuza", "fifiuza",
                "senha123", new Date(), "endereco",UserType.CUSTOMER);
        assertThatCode(() -> UserValidation.validation(user)).doesNotThrowAnyException();
    }
}
