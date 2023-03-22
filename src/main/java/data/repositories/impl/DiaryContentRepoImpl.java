package data.repositories.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.models.DiaryContent;
import data.repositories.DiaryContentRepo;

import java.util.ArrayList;
import java.util.List;

public class DiaryContentRepoImpl implements DiaryContentRepo {
    private List<DiaryContent>diaryContents = new ArrayList<>();
    @Override
    public CreateDiaryContentResponse createDiaryContent(CreateDiaryContentRequest createDiaryContentRequest) {
        DiaryContent diaryContent = new DiaryContent();
        long id = diarySize() + 1;
        diaryContent.setBody(createDiaryContentRequest.getBody());
        diaryContent.setTitle(createDiaryContentRequest.getTitle());
        diaryContent.setId(id);
        diaryContents.add(diaryContent);
        CreateDiaryContentResponse createDiaryContentResponse = new CreateDiaryContentResponse();
        createDiaryContentResponse.setId(id);
        createDiaryContentResponse.setMessage("Created successfully");
        return createDiaryContentResponse;
    }

    @Override
    public void deleteDiaryContentById(long id) {

    }

    @Override
    public List<DiaryContent> viewAllDiaryContent() {
        return null;
    }

    @Override
    public DiaryContent viewDiaryContentById(long id) {
        return null;
    }

    @Override
    public DiaryContent viewDiaryContentByDate(String date) {
        return null;
    }

    @Override
    public DiaryContent viewDiaryContentByTitle(String title) {
        return null;
    }

    @Override
    public int diarySize() {
        return diaryContents.size();
    }
}
