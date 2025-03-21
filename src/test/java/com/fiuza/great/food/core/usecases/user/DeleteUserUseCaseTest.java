package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;
import com.fiuza.great.food.core.dto.request.user.UserPasswordDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.helper.dto.user.UserDeleteDtoHelper;
import com.fiuza.great.food.helper.dto.user.UserPasswordDtoHelper;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {

    @Mock
    UserGateway userGateway;

    @InjectMocks
    DeleteUserUseCase deleteUserUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        deleteUserUseCase = new DeleteUserUseCase(userGateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void delete_user_with_success() {
        // arrange
        UserDeleteDto userDeleteDto = UserDeleteDtoHelper.defaultDto();
        User userResult = UserHelper.createUserWithId();

        when(userGateway.findUserByEmail(userDeleteDto.email())).thenReturn(Optional.of(userResult));
        doNothing().when(userGateway).delete(any(Long.class));

        // act
        deleteUserUseCase.execute(userDeleteDto);

        // assert
        verify(userGateway, times(1)).delete(any(Long.class));
    }
}
