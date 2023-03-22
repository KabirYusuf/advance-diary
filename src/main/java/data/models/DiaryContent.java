package data.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DiaryContent {
    private long id;
    private String title;
    private String body;
    private LocalDateTime localDateTime;
}
