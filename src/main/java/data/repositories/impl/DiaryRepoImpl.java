package data.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import data.models.DiaryContent;
import data.repositories.DiaryRepo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiaryRepoImpl implements DiaryRepo {
    private final List<DiaryContent>diaryContents = new ArrayList<>();
    @Override
    public DiaryContent saveDiaryContent(DiaryContent diaryContent) {
        int id = diarySize() + 1;
        diaryContent.setId(id);
        diaryContents.add(diaryContent);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
//        try {
//            mapper.writeValue(new File("C:\\Users\\user\\IdeaProjects\\advancediary\\" +
//                    "src\\main\\resources\\DiaryFile.txt"), diaryContents);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\user\\IdeaProjects\\advancediary" +
                "\\src\\main\\resources\\DiaryFile.txt"))) {
            oos.writeObject(diaryContents);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public String updateDiaryContent(DiaryContent diaryContent) {
        diaryContents.add(diaryContent.getId() - 1,diaryContent);
        return "Content updated successfully";
    }
}
