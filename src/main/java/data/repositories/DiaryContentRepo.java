package data.repositories;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.models.DiaryContent;

import java.util.List;

public interface DiaryContentRepo {
    CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest);
    void deleteDiaryContentById(long id);
    List<DiaryContent> viewAllDiaryContent();
    DiaryContent viewDiaryContentById(long id);
    DiaryContent viewDiaryContentByDate(String date);
    DiaryContent viewDiaryContentByTitle(String title);
    int diarySize();
}
