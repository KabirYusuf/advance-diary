package services.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.CreateUserRequest;
import data.dto.response.CreateDiaryContentResponse;
import exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.UserService;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
private UserService userService;
private CreateUserRequest createUserRequest;
private CreateUserRequest secondUser;
private CreateDiaryContentRequest createDiaryContentRequest;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        createUserRequest = new CreateUserRequest();
        secondUser = new CreateUserRequest();
        createDiaryContentRequest = new CreateDiaryContentRequest();
        createDiaryContentRequest.setUserEmail("kabir@gmail.com");
        createDiaryContentRequest.setTitle("User title");
        createDiaryContentRequest.setBody("What is it all about");
        createUserRequest.setEmail("kabir@gmail.com");
        createUserRequest.setPassword("1234");
        secondUser.setEmail("kabir@gmail.com");
        secondUser.setPassword("1234");
    }
    @Test
    void testThatAnExceptionIsThrownIfAUserTriesToRegisterWithAnEmailThatIsAlreadyExisting(){
        userService.register(createUserRequest);
        assertThrows(UserException.class, ()-> userService.register(secondUser));
    }
    @Test
    void testThatASuccessMessageIsReturnedWhenAUserSuccessfullyCreateAndSavesAContent(){
        CreateDiaryContentResponse createDiaryContentResponse = userService.createDiaryContent(createDiaryContentRequest);
        assertEquals("Content saved successfully", createDiaryContentResponse.getMessage());
    }
}