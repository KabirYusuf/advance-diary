package data.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import data.models.User;
import data.repositories.UserRepo;
import exception.UserException;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepoImpl implements UserRepo {
    List<User> users = new ArrayList<>();
    @Override
    public void saveUser(User user) {
        users.add(user);
        ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writeValue(new File("C:\\Users\\user\\IdeaProjects\\advancediary\\" +
//                    "src\\main\\resources\\UserFile.txt"), users);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\user\\IdeaProjects\\" +
                "advancediary\\src\\main\\resources\\UserFile.txt"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user:users) {
            if (user.getEmail().equalsIgnoreCase(email))return user;
        }
        return null;
    }

    @Override
    public int getUserDBSize() {
        return users.size();
    }
}