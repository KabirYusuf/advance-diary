package services.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.CreateUserRequest;
import data.dto.request.LoginRequest;
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
    @Test
    void testThatIfUserCredentialsAreCorrect_TheUserCanLogin(){
        userService.register(createUserRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailAddress("kabir@gmail.com");
        loginRequest.setPassword("1234");
        assertEquals("successful", userService.login(loginRequest));
    }
    @Test
    void testThatIfOneOfUserEmailIsNotCorrect_TheUserCantLogin(){
        userService.register(createUserRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailAddress("abir@gmail.com");
        loginRequest.setPassword("1234");
        assertEquals("unsuccessful", userService.login(loginRequest));
    }
    @Test
    void testThatIfOneOfUserPasswordIsNotCorrect_TheUserCantLogin(){
        userService.register(createUserRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmailAddress("kabir@gmail.com");
        loginRequest.setPassword("134");
        assertEquals("unsuccessful", userService.login(loginRequest));
    }
    @Test
    void testThatAUserDiaryContentsIsReturnedWhenTheUserViewsAllDiaryContent(){
        userService.register(createUserRequest);
        CreateUserRequest createUserRequest1 = new CreateUserRequest();
        CreateDiaryContentRequest createDiaryContentRequest1 = new CreateDiaryContentRequest();
        createUserRequest1.setEmail("ade@gmail.com");
        createUserRequest1.setPassword("2121");
        userService.register(createUserRequest1);
        userService.createDiaryContent(createDiaryContentRequest);
        createDiaryContentRequest1.setBody("Second Content");
        createDiaryContentRequest1.setUserEmail("ade@gmail.com");
        createDiaryContentRequest1.setTitle("second title");
        userService.createDiaryContent(createDiaryContentRequest1);
        assertEquals(1,userService.viewDairyContents("kabir@gmail.com").size());
        assertEquals(1, userService.viewDairyContents("ade@gmail.com").size());
    }
}