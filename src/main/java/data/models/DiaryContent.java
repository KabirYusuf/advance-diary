package data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@ToString
public class DiaryContent implements Serializable {
    private int id;
    private String title;
    private String body;
    private String userEmail;
    private LocalDateTime localDateTime;
}
