package services.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.request.UpdateContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.models.DiaryContent;
import data.repositories.DiaryRepo;
import data.repositories.impl.DiaryRepoImpl;
import services.DiaryService;

import java.time.LocalDateTime;
import java.util.List;

public class DiaryServiceImpl implements DiaryService {
    private  DiaryRepo diaryRepo = new DiaryRepoImpl();
    @Override
    public CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest) {
        DiaryContent diaryContent = new DiaryContent();
        diaryContent.setBody(createDiaryContentRequest.getBody());
        diaryContent.setTitle(createDiaryContentRequest.getTitle());
        diaryContent.setLocalDateTime(LocalDateTime.now());
        diaryContent.setUserEmail(createDiaryContentRequest.getUserEmail());
        DiaryContent savedDiaryContent = diaryRepo.saveDiaryContent(diaryContent);
        CreateDiaryContentResponse createDiaryContentResponse = new CreateDiaryContentResponse();
        createDiaryContentResponse.setMessage("Content saved successfully");
        createDiaryContentResponse.setId(savedDiaryContent.getId());
        return createDiaryContentResponse;
    }

    @Override
    public List<DiaryContent> viewAllDiaryContents() {
        return diaryRepo.viewAllDiaryContent();
    }

    @Override
    public List<DiaryContent> findDiaryContentByDate(String date) {
        return diaryRepo.viewDiaryContentByDate(date);
    }

    @Override
    public List<DiaryContent> findDiaryContentByTitle(String title) {
        return diaryRepo.viewDiaryContentByTitle(title);
    }

    @Override
    public String updateDiaryContent(UpdateContentRequest updateContentRequest) {
        DiaryContent diaryContent = new DiaryContent();
        diaryContent.setId(updateContentRequest.getId());
        diaryContent.setBody(updateContentRequest.getBody());
        diaryContent.setTitle(updateContentRequest.getTitle());
        return diaryRepo.updateDiaryContent(diaryContent);
    }

    @Override
    public void deleteDiaryContentById(int id) {
        diaryRepo.deleteDiaryContentById(id);
    }

    @Override
    public DiaryContent findDiaryContentByID(int id) {
        return diaryRepo.viewDiaryContentById(id);
    }

    @Override
    public int getSizeOfDiaryContents() {
        return diaryRepo.diarySize();
    }
}
