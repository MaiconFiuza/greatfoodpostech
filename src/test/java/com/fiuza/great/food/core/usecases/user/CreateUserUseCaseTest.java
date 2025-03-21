package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.InternalServerError;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.exceptions.NullDataNotNullException;
import com.fiuza.great.food.core.exceptions.WrongTypeOfUserException;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.helper.dto.user.UserDtoHelper;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {
    Clock fixedClock = Clock.fixed(Instant.parse("2024-03-21T10:15:30.00Z"), ZoneId.of("UTC"));

    @Mock
    UserGateway userGateway;
    @InjectMocks
    CreateUserUseCase createUserUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        createUserUseCase = new CreateUserUseCase(userGateway, fixedClock);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void create_user_with_success() {
        // arrange
        UserDto userDto = UserDtoHelper.defaultDto();
        User userResult = UserHelper.createUserWithId();

        Date expectedDate = Date.from(Instant.now(fixedClock));

        when(userGateway.findUserByEmailValidation(userDto.email())).thenReturn(false);
        when(userGateway.save(any(User.class))).thenReturn(userResult);

        // act
        var savedUser = createUserUseCase.execute(userDto);

        // assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userGateway, times(1)).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getLastModification()).isCloseTo(expectedDate, 1000); // Aceita 1 segundo de diferença
        assertThat(capturedUser.getName()).isEqualTo(userDto.name());
        assertThat(capturedUser.getEmail()).isEqualTo(userDto.email());
        assertThat(savedUser).isNotNull();
    }

    @Test
    void create_user_should_fail_with_null_data() {
        // arrange
        UserDto userDto = UserDtoHelper.withNull();

        when(userGateway.findUserByEmailValidation(userDto.email())).thenReturn(false);

        // act
        assertThatThrownBy(
                () -> createUserUseCase.execute(userDto))
                .isInstanceOf(NullDataNotNullException.class)
                .hasMessage("Todos os campos devem ser enviados");

        // assert
        verify(userGateway, times(0)).findUserByEmail(any(String.class));
    }

    @Test
    void create_user_should_fail_with_internal_server_error() {
        // arrange
        UserDto userDto = UserDtoHelper.withNull();

        when(userGateway.findUserByEmailValidation(userDto.email())).thenThrow(new InternalServerError("Usuário não encontrado"));

        // act
        assertThatThrownBy(
                () -> createUserUseCase.execute(userDto))
                .isInstanceOf(InternalServerError.class);

        // assert
        verify(userGateway, times(0)).findUserByEmail(any(String.class));
    }
}

