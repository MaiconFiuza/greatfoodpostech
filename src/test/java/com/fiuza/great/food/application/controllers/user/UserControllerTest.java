package com.fiuza.great.food.application.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiuza.great.food.application.controllers.UserController;
import com.fiuza.great.food.application.handlers.ControllerExceptionHandler;
import com.fiuza.great.food.core.dto.request.user.UserDeleteDto;
import com.fiuza.great.food.core.dto.request.user.UserDto;
import com.fiuza.great.food.core.dto.request.user.UserPasswordDto;
import com.fiuza.great.food.core.dto.request.user.UserUpdateDto;
import com.fiuza.great.food.core.entities.user.User;
import com.fiuza.great.food.core.exceptions.CannotUseSameEmailException;
import com.fiuza.great.food.core.exceptions.IncorrectPasswordException;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.exceptions.NullDataNotNullException;
import com.fiuza.great.food.core.gateway.UserGateway;
import com.fiuza.great.food.core.usecases.user.*;
import com.fiuza.great.food.helper.dto.user.UserDeleteDtoHelper;
import com.fiuza.great.food.helper.dto.user.UserDtoHelper;
import com.fiuza.great.food.helper.dto.user.UserPasswordDtoHelper;
import com.fiuza.great.food.helper.dto.user.UserUpdatedDtoHelper;
import com.fiuza.great.food.helper.entities.user.UserHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;



import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    UserGateway userGateway;

    @Mock
    CreateUserUseCase createUserUseCase;
    @Mock
    GetUserByIdUseCase getUserByIdUseCase;
    @Mock
    UpdateUserUseCase updateUserUseCase;
    @Mock
    ChangePasswordUseCase changePasswordUseCase;
    @Mock
    DeleteUserUseCase deleteUserUseCase;
    @Mock
    private MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        userController = new UserController(
                createUserUseCase,getUserByIdUseCase,updateUserUseCase,changePasswordUseCase,deleteUserUseCase
        );
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }


    @Test
    public void create_user_with_success() throws Exception {
        //arrange
        UserDto userDto = UserDtoHelper.defaultDto();
        User user = UserHelper.createUserWithId();

        when(createUserUseCase.execute(any(UserDto.class))).thenReturn(user);

        //act & assert
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

        verify(createUserUseCase, times(1)).execute(any(UserDto.class));
    }

    @Test
    public void get_user_by_id_with_success() throws Exception {
        //arrange
        Long id = 1L;
        User user = UserHelper.createUserWithId();

        when(getUserByIdUseCase.execute(any(Long.class))).thenReturn(user);

        //act & assert
        mockMvc.perform(get("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(getUserByIdUseCase, times(1)).execute(any(Long.class));
    }

    @Test
    public void update_user_by_id_with_success() throws Exception {
        //arrange
        Long id = 1L;
        UserUpdateDto userUpdateDto = UserUpdatedDtoHelper.defaultDto();
        User user = UserHelper.createUserWithId();

        when(updateUserUseCase.execute(any(UserUpdateDto.class), eq(id))).thenReturn(user);

        //act & assert
        mockMvc.perform(put("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDto)))
                .andExpect(status().isOk());

        verify(updateUserUseCase, times(1)).execute(any(UserUpdateDto.class), eq(id));
    }

    @Test
    public void update_user_password_with_success() throws Exception {
        //arrange
        Long id = 1L;
        UserPasswordDto userPasswordDto = UserPasswordDtoHelper.defaultDto();
        User user = UserHelper.createUserWithId();

        when(changePasswordUseCase.execute(any(Long.class),any(UserPasswordDto.class) )).thenReturn(user);

        //act & assert
        mockMvc.perform(put("/user/{id}/password", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userPasswordDto)))
                .andExpect(status().isOk());

        verify(changePasswordUseCase, times(1)).execute(eq(id),any(UserPasswordDto.class));
    }

    @Test
    public void delete_user_with_success() throws Exception {
        //arrange
        Long id = 1L;
        UserDeleteDto userDeleteDto = UserDeleteDtoHelper.defaultDto();
        User user = UserHelper.createUserWithId();

        doNothing().when(deleteUserUseCase).execute(any(UserDeleteDto.class));

        //act & assert
        mockMvc.perform(delete("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDeleteDto)))
                .andExpect(status().isNoContent());

        verify(deleteUserUseCase, times(1)).execute(any(UserDeleteDto.class));
    }


    @Test
    public void get_user_by_id_should_fail_with_not_found_exception() throws Exception {
        //arrange
        Long id = 7L;

        when(getUserByIdUseCase.execute(any(Long.class)))
                .thenThrow(new NotFoundException("Usuário não encontrado"));

        //act & assert
        mockMvc.perform(get("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(getUserByIdUseCase, times(1)).execute(any(Long.class));
    }

    @Test
    public void create_user_should_fail_with_cannot_use_same_email_exception() throws Exception {
        //arrange
        UserDto userDto = UserDtoHelper.defaultDto();

        when(createUserUseCase.execute(any(UserDto.class)))
                .thenThrow(new CannotUseSameEmailException("O email indicado já está em uso"));

        //act & assert
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());

        verify(createUserUseCase, times(1)).execute(any(UserDto.class));
    }

    @Test
    public void create_user_should_fail_with_not_null_exception() throws Exception {
        //arrange
        UserDto userDto = UserDtoHelper.withNull();

        when(createUserUseCase.execute(any(UserDto.class)))
                .thenThrow(new NullDataNotNullException("email"));

        //act & assert
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());

        verify(createUserUseCase, times(1)).execute(any(UserDto.class));
    }

    @Test
    public void update_user_password_should_fail_with_incorrect_password_exception() throws Exception {
        //arrange
        Long id = 1L;
        UserUpdateDto userUpdateDto = UserUpdatedDtoHelper.defaultDto();
        User user = UserHelper.createUserWithId();

        when(updateUserUseCase.execute(any(UserUpdateDto.class), eq(id)))
                .thenThrow(new IncorrectPasswordException("Senha incorreta"));

        //act & assert
        mockMvc.perform(put("/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateDto)))
                .andExpect(status().isBadRequest());

        verify(updateUserUseCase, times(1)).execute(any(UserUpdateDto.class), eq(id));
    }



}
