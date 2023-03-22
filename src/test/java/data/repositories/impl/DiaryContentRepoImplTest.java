package data.repositories.impl;
import data.dto.request.UpdateContentRequest;
import data.models.DiaryContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DiaryContentRepoImplTest {
    private DiaryContentRepoImpl diaryContentRepoImpl;
    private DiaryContent diaryContent;

    @BeforeEach
    void setUp() {
        diaryContentRepoImpl = new DiaryContentRepoImpl();
        diaryContent = new DiaryContent();
        diaryContent.setTitle("First content");
        diaryContent.setBody("My first diary content");
    }
    @Test
    void testThatIfADiaryContentIsSaved_DiaryContentDBSizeIncreasesByOne(){
        var diaryDBSizeBeforeSavingContent = diaryContentRepoImpl.diarySize();
        assertEquals(0,diaryDBSizeBeforeSavingContent);
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        var diaryDBSizeAfterSavingContent = diaryContentRepoImpl.diarySize();
        assertEquals(1,diaryDBSizeAfterSavingContent);
    }
    @Test
    void testThatIdOfDiaryContentIsGeneratedAutomaticallyWhenContentIsSaved(){
        DiaryContent saveDiaryContent = diaryContentRepoImpl.saveDiaryContent(diaryContent);
        assertEquals(1, saveDiaryContent.getId());
    }
    @Test
    void testThatDiaryContentDBSizeReducesByOneWhenADiaryContentIsDeletedById(){
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        int dBSizeBeforeDeletingAContent = diaryContentRepoImpl.diarySize();
        assertEquals(2,dBSizeBeforeDeletingAContent);
        diaryContentRepoImpl.deleteDiaryContentById(1);
        int dBSizeAfterDeletingAContent = diaryContentRepoImpl.diarySize();
        assertEquals(1, dBSizeAfterDeletingAContent);
    }
    @Test
    void testThatTheNumberOfContentsInDBIsReturnedWhenAllContentsAreViewed(){
        var sizeOfContentsReturnedBeforeAddingContents = diaryContentRepoImpl.viewAllDiaryContent();
        assertEquals(0,sizeOfContentsReturnedBeforeAddingContents.size());
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        var sizeOfContentsReturnedAfterAddingAContent = diaryContentRepoImpl.viewAllDiaryContent();
        assertEquals(1,sizeOfContentsReturnedAfterAddingAContent.size());
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        var sizeOfContentsReturnedAfterAddingTwoContent = diaryContentRepoImpl.viewAllDiaryContent();
        assertEquals(2,sizeOfContentsReturnedAfterAddingTwoContent.size());
    }
    @Test
    void testThatAllContentsWithSameDateAreReturnedWhenContentsAreSearchedForById(){
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        int noOfContentsWhenISearchByDate = diaryContentRepoImpl.viewDiaryContentByDate(LocalDate.now().toString()).size();
        assertEquals(2, noOfContentsWhenISearchByDate);
    }
    @Test
    void testThatIfADiaryContentCanBeUpdated(){
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        String titleOfSavedContentBeforeUpdate = "First content";
        String bodyOfContentBeforeUpdate = "My first diary content";
        assertEquals(bodyOfContentBeforeUpdate, diaryContentRepoImpl.viewDiaryContentById(1).getBody());
        assertEquals(titleOfSavedContentBeforeUpdate, diaryContentRepoImpl.viewDiaryContentById(1).getTitle());
        String titleUpdate = "updated title";
        String bodyUpdate = "updatedBody";
        UpdateContentRequest updateContentRequest = new UpdateContentRequest();
        updateContentRequest.setBody(bodyUpdate);
        updateContentRequest.setId(1);
        updateContentRequest.setTitle(titleUpdate);
        diaryContentRepoImpl.updateDiaryContent(updateContentRequest);
        String titleOfSavedContentAfterUpdate = "updated title";
        String bodyOfContentAfterUpdate = "updatedBody";
        assertEquals(titleOfSavedContentAfterUpdate,diaryContentRepoImpl.viewDiaryContentById(1).getTitle() );
        assertEquals(bodyOfContentAfterUpdate, diaryContentRepoImpl.viewDiaryContentById(1).getBody());
    }
    @Test
    void testThatAllDiaryContentsHavingTitleUsedToSearchAreReturned(){
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        diaryContentRepoImpl.saveDiaryContent(diaryContent);
        int noOfContentsWhenISearchByTitle = diaryContentRepoImpl.viewDiaryContentByTitle("First content").size();
        assertEquals(2, noOfContentsWhenISearchByTitle);
    }
}