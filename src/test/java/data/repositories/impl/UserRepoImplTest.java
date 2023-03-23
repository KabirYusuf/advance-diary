package data.repositories.impl;

import data.models.User;
import data.repositories.UserRepo;
import exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepoImplTest {
    private UserRepo userRepo;
    private User user;

    @BeforeEach
    void setUp() {
        userRepo = new UserRepoImpl();
        user = new User();
        user.setEmail("kabir@gmail.com");
        user.setPassword("1234");
    }
    @Test
    void testThatUserDBSizeIncreasesByOneWhenAUserIsSaved(){
        int sizeOfDBBeforeSavingAUser = userRepo.getUserDBSize();
        assertEquals(0, sizeOfDBBeforeSavingAUser);
        userRepo.saveUser(user);
        assertEquals(1, userRepo.getUserDBSize());
    }
    @Test
    void testThatAUserCanBeFoundByEmail(){
        userRepo.saveUser(user);
        User foundUser = userRepo.findUserByEmail("kabir@gmail.com");
        assertNotNull(foundUser);
    }
    @Test
    void testThatAnExceptionIsThrownIfAUserWithTheEmailAddressSuppliedDoesntExist(){
        assertThrows(UserException.class, ()->userRepo.findUserByEmail("hdbc@gmail.com"));
    }
}