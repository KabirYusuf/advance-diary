package services;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.CreateUserRequest;
import data.dto.request.LoginRequest;
import data.dto.request.UpdateContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.dto.response.CreateUserResponse;
import data.models.DiaryContent;
import data.models.User;

import java.util.List;

public interface UserService {
    CreateUserResponse register(CreateUserRequest createUserRequest);
    User findUserByEmail(String email);
    CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest);
    String login(LoginRequest loginRequest);
    List<DiaryContent> viewDairyContents(String email);
    String updateContent(UpdateContentRequest updateContentRequest);
    List<DiaryContent> findDiaryContentByTitle(String title, String email);

}
