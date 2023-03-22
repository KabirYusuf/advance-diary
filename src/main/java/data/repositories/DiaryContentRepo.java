package data.repositories;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.models.DiaryContent;

import java.util.List;

public interface DiaryContentRepo {
    DiaryContent saveDiaryContent(DiaryContent diaryContent);
    void deleteDiaryContentById(int id);
    List<DiaryContent> viewAllDiaryContent();
    DiaryContent viewDiaryContentById(long id);
    List<DiaryContent> viewDiaryContentByDate(String date);
    DiaryContent viewDiaryContentByTitle(String title);
    int diarySize();
}
