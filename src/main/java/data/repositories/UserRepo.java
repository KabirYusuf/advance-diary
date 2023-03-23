package data.repositories;

import data.models.User;

import java.util.Optional;

public interface UserRepo {
    void saveUser(User user);
    User findUserByEmail(String email);
    int getUserDBSize();
}
