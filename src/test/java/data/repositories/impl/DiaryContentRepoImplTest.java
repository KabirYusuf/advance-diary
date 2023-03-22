package data.repositories.impl;
import data.models.DiaryContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        int noOfContentsWhenISearchByDate = diaryContentRepoImpl.viewDiaryContentByDate("2023-03-22").size();
        assertEquals(2, noOfContentsWhenISearchByDate);
    }
}