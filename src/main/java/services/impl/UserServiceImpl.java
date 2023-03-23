package services.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.CreateUserRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.dto.response.CreateUserResponse;
import data.models.User;
import data.repositories.UserRepo;
import data.repositories.impl.UserRepoImpl;
import exception.UserException;
import services.DiaryService;
import services.UserService;

public class UserServiceImpl implements UserService {
    private UserRepo userRepo = new UserRepoImpl();
    private DiaryService diaryService = new DiaryServiceImpl();
    @Override
    public CreateUserResponse register(CreateUserRequest createUserRequest) {
        if (userRepo.findUserByEmail(createUserRequest.getEmail()) != null){
            throw new UserException("A user with this " + createUserRequest.getEmail()
                    + "is already existing, try using another email");
        }
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        userRepo.saveUser(user);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUserEmail(createUserRequest.getEmail());
        createUserResponse.setMessage("User created successfully");
        return createUserResponse;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest) {
        return diaryService.createDiaryContent(createDiaryContentRequest);
    }
}
