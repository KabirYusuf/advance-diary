package services;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.CreateUserRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.dto.response.CreateUserResponse;
import data.models.User;

public interface UserService {
    CreateUserResponse register(CreateUserRequest createUserRequest);
    User findUserByEmail(String email);
    CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest);
}
