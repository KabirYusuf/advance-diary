package services;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.UpdateContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.models.DiaryContent;

import java.util.List;

public interface DiaryService {
    CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest);
    List<DiaryContent> viewAllDiaryContents();
    List<DiaryContent> findDiaryContentByDate(String date);
    List<DiaryContent> findDiaryContentByTitle(String title);
    String updateDiaryContent(UpdateContentRequest updateContentRequest);
    void deleteDiaryContentById(int id);
    DiaryContent findDiaryContentByID(int id);
    int getSizeOfDiaryContents();

}
