package com.fiuza.great.food.core.presenters.user;

import com.fiuza.great.food.core.dto.response.user.UserDtoResponse;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPresenterTest {
    @Test
    public void should_return_user_dto() {
        User user = UserHelper.createUserWithNullRestaurant();
        UserDtoResponse userDtoResponse = UserPresenter.toUserDtoResponse(user);
        assertThat(userDtoResponse.restaurants()).isEmpty();
    }
}
