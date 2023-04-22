package data.repositories.impl;

import data.models.User;
import data.repositories.UserRepo;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepoImpl implements UserRepo {
    private List<User> users = new ArrayList<>();

    @Override
    public void saveUser(User user) {
        users.add(user);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\user\\IdeaProjects\\" +
                "advancediary\\src\\main\\resources\\UserFile.txt"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) return user;
        }
        return null;
    }

    @Override
    public int getUserDBSize() {
        return users.size();
    }

    @Override
    public List<User> getAllUsers() {
        File file = new File("C:\\Users\\user\\IdeaProjects\\advancediary\\src\\main\\resources\\UserFile.txt");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            users = (List<User>) objectInputStream.readObject();
        } catch (IOException  | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null && objectInputStream != null) {
                try {
                    fileInputStream.close();
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }
}
