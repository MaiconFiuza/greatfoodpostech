package com.fiuza.great.food.infra.adapter.repository;

import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import com.fiuza.great.food.helper.model.UserModelHelper;
import com.fiuza.great.food.infra.model.UserModel;
import com.fiuza.great.food.infra.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImpTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserRepositoryImp userRepositoryImp;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        userRepositoryImp = new UserRepositoryImp(userRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void save_success() {
        // arrange
        User userDefault = UserHelper.createUserDefault();
        UserModel userModel = UserModelHelper.createUserDefault();
        User userResult = UserHelper.createUserWithId();


        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        // act
        var savedUser = userRepositoryImp.save(userDefault);

        // assert
        ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        UserModel capturedUser = userCaptor.getValue();

        assertThat(capturedUser.getName()).isEqualTo(userResult.getName());
        assertThat(capturedUser.getEmail()).isEqualTo(userResult.getEmail());
        assertThat(savedUser).isNotNull();
    }
}
