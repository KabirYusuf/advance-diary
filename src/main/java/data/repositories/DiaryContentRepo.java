package data.repositories;

import data.dto.request.UpdateContentRequest;
import data.models.DiaryContent;

import java.util.List;

public interface DiaryContentRepo {
    DiaryContent saveDiaryContent(DiaryContent diaryContent);
    void deleteDiaryContentById(int id);
    List<DiaryContent> viewAllDiaryContent();
    DiaryContent viewDiaryContentById(int id);
    List<DiaryContent> viewDiaryContentByDate(String date);
    List<DiaryContent> viewDiaryContentByTitle(String title);
    int diarySize();
    DiaryContent updateDiaryContent(UpdateContentRequest updateContentRequest);
}
