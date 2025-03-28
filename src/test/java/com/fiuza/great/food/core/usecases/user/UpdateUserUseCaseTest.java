package com.fiuza.great.food.core.usecases.user;

import com.fiuza.great.food.core.dto.request.user.UserUpdateDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.helper.dto.user.UserUpdatedDtoHelper;
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

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {
    Clock fixedClock = Clock.fixed(Instant.parse("2024-03-21T10:15:30.00Z"), ZoneId.of("UTC"));

    @Mock
    UserGateway userGateway;

    @InjectMocks
    UpdateUserUseCase updateUserUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        updateUserUseCase = new UpdateUserUseCase(userGateway, fixedClock);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void update_user_with_success() {
        // arrange
        UserUpdateDto userUpdateDto = UserUpdatedDtoHelper.defaultDto();
        User userResult = UserHelper.createUserWithId();

        Date expectedDate = Date.from(Instant.now(fixedClock));

        when(userGateway.findUserById(userResult.getId())).thenReturn(Optional.of(userResult));
        when(userGateway.update(any(User.class))).thenReturn(userResult);

        // act
        var savedUser = updateUserUseCase.execute(userUpdateDto, userResult.getId());

        // assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userGateway, times(1)).update(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getLastModification()).isCloseTo(expectedDate, 1000); // Aceita 1 segundo de diferença
        assertThat(capturedUser.getName()).isEqualTo(userUpdateDto.name());
        assertThat(capturedUser.getEmail()).isEqualTo(userUpdateDto.email());
        assertThat(savedUser).isNotNull();
    }
}
