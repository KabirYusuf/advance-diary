package services.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.CreateUserRequest;
import data.dto.request.LoginRequest;
import data.dto.request.UpdateContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.dto.response.CreateUserResponse;
import data.models.DiaryContent;
import data.models.User;
import data.repositories.UserRepo;
import data.repositories.impl.UserRepoImpl;
import exception.UserException;
import org.mindrot.jbcrypt.BCrypt;
import services.DiaryService;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

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
        user.setPassword(BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt()));
        userRepo.saveUser(user);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUserEmail(createUserRequest.getEmail());
        createUserResponse.setMessage("User created successfully");
        return createUserResponse;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest) {
        return diaryService.createDiaryContent(createDiaryContentRequest);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        String response = "";
        User foundUser = findUserByEmail(loginRequest.getEmailAddress());
        if (foundUser != null && BCrypt.checkpw(loginRequest.getPassword(),foundUser.getPassword()))response = "successful";
        else response = "unsuccessful";
        return response;
    }

    @Override
    public List<DiaryContent> viewDairyContents(String email) {
        return diaryService.
                viewAllDiaryContents().
                stream().
                filter(diaryContent -> diaryContent.getUserEmail().equals(email)).
                toList();
    }

    @Override
    public String updateContent(UpdateContentRequest updateContentRequest) {
        return diaryService.updateDiaryContent(updateContentRequest);
    }

    @Override
    public List<DiaryContent> findDiaryContentByTitle(String title, String email) {
        var UsersDiaryContent = viewDairyContents(email);
        List<DiaryContent> contentsByTitle = new ArrayList<>();
        for (DiaryContent content: UsersDiaryContent) {
            if (content.getTitle().equals(title))contentsByTitle.add(content);
        }
        return contentsByTitle;

    }

}
