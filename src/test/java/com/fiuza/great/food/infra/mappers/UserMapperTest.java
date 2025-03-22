package com.fiuza.great.food.infra.mappers;

import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import com.fiuza.great.food.helper.model.UserModelHelper;
import com.fiuza.great.food.infra.model.UserModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

    @Test
    public void should_return_user_model() {
        User user = UserHelper.createUserWithRestaurant();
        UserModel result = UserMapper.userToUserModel(user);

        assertThat(result.getRestaurants()).isNotNull();
    }

    @Test
    public void should_return_user() {
        UserModel userModel = UserModelHelper.createUserWithRestaurant();
        User result = UserMapper.userModelToUser(userModel);

        assertThat(result.getRestaurants()).isNotNull();
    }
}
