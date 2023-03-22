package data.repositories.impl;

import data.dto.request.UpdateContentRequest;
import data.models.DiaryContent;
import data.repositories.DiaryContentRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiaryContentRepoImpl implements DiaryContentRepo {
    private final List<DiaryContent>diaryContents = new ArrayList<>();
    @Override
    public DiaryContent saveDiaryContent(DiaryContent diaryContent) {
        int id = diarySize() + 1;
        diaryContent.setId(id);
        diaryContent.setLocalDateTime(LocalDateTime.now());
        diaryContents.add(diaryContent);
        return diaryContent;
    }

    @Override
    public void deleteDiaryContentById(int id) {
        diaryContents.remove(id - 1);
    }

    @Override
    public List<DiaryContent> viewAllDiaryContent() {
        return diaryContents;
    }

    @Override
    public DiaryContent viewDiaryContentById(int id) {
        return diaryContents.get(id - 1);
    }

    @Override
    public List<DiaryContent> viewDiaryContentByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate actualDate = LocalDate.parse(date, formatter);
        return diaryContents.
                stream().
                filter(diaryContent -> diaryContent.getLocalDateTime().toLocalDate().equals(actualDate)).
                toList();
    }

    @Override
    public List<DiaryContent> viewDiaryContentByTitle(String title) {
        return diaryContents.
                stream().
                filter(diaryContent -> diaryContent.getTitle().equals(title)).
                toList();
    }

    @Override
    public int diarySize() {
        return diaryContents.size();
    }

    @Override
    public DiaryContent updateDiaryContent(UpdateContentRequest updateContentRequest) {
        DiaryContent diaryContent = new DiaryContent();
        for (int i = 0; i < diaryContents.size(); i++) {
            if (diaryContents.get(i).getId() == updateContentRequest.getId()){
                diaryContent.setId(diaryContents.get(i).getId());
                diaryContent.setTitle(updateContentRequest.getTitle());
                diaryContent.setBody(updateContentRequest.getBody());
                diaryContents.add(i,diaryContent);
                break;
            }
        }
        return diaryContent;
    }
}
