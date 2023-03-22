package data.repositories.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryContentRepoImplTest {
    private DiaryContentRepoImpl diaryContentRepoImpl = new DiaryContentRepoImpl();
    private CreateDiaryContentRequest createDiaryContentRequest = new CreateDiaryContentRequest();

    @BeforeEach
    void setUp() {
        createDiaryContentRequest.setTitle("First Content");
        createDiaryContentRequest.setBody("My first diary content");
    }
    @Test
    void testThatIfADiaryContentIsSaved_DiaryContentDBSizeIncreasesByOne(){
        var diaryDBSizeBeforeSavingContent = diaryContentRepoImpl.diarySize();
        assertEquals(0,diaryDBSizeBeforeSavingContent);
        diaryContentRepoImpl.createDiaryContent(createDiaryContentRequest);
        var diaryDBSizeAfterSavingContent = diaryContentRepoImpl.diarySize();
        assertEquals(1,diaryDBSizeAfterSavingContent);
    }
    @Test
    void testThatIdOfDiaryContentIsGeneratedAutomaticallyWhenContentIsSaved(){
        CreateDiaryContentResponse createDiaryContentResponse = diaryContentRepoImpl.createDiaryContent(createDiaryContentRequest);
        assertEquals(1, createDiaryContentResponse.getId());
    }
    @Test
    void testThatSuccessMessageIsReturnedWhenDiaryContentIsAddedSuccessfully(){
        CreateDiaryContentResponse createDiaryContentResponse = diaryContentRepoImpl.createDiaryContent(createDiaryContentRequest);
        assertEquals("Created successfully", createDiaryContentResponse.getMessage());
    }
}