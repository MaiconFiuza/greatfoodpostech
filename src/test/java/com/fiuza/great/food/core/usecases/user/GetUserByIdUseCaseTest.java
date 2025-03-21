package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.helper.dto.user.UserDtoHelper;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdUseCaseTest {
    @Mock
    UserGateway userGateway;
    GetUserByIdUseCase getUserByIdUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        getUserByIdUseCase = new GetUserByIdUseCase(userGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void get_user_by_id_with_success() {
        // arrange
        User userResult = UserHelper.createUserWithId();

        when(userGateway.findUserById(any(Long.class))).thenReturn(Optional.of(userResult));
        // act
        var savedUser = getUserByIdUseCase.execute(userResult.getId());

        // assert
        verify(userGateway, times(1)).findUserById(userResult.getId());
        assertThat(savedUser).isNotNull().isEqualTo(userResult);
    }

    @Test
    void should_fail_with_not_found_exception()  {
        // arrange
        var userId = 2L;
        when(userGateway.findUserById(userId)).thenThrow(new NotFoundException("Usuário não encontrado"));

        // act
        assertThatThrownBy(
                () -> getUserByIdUseCase.execute(userId))
            .isInstanceOf(NotFoundException.class)
                .hasMessage("Usuário não encontrado");

        // assert
        verify(userGateway, times(1)).findUserById(userId);
    }
}
