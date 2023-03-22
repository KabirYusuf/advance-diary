package data.repositories.impl;

import data.dto.request.CreateDiaryContentRequest;
import data.dto.response.CreateDiaryContentResponse;
import data.models.DiaryContent;
import data.repositories.DiaryContentRepo;

import javax.swing.text.DateFormatter;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
    public DiaryContent viewDiaryContentById(long id) {
        return null;
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
    public DiaryContent viewDiaryContentByTitle(String title) {
        return null;
    }

    @Override
    public int diarySize() {
        return diaryContents.size();
    }
}
