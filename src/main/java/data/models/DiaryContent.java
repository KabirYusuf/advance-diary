package data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@ToString
public class DiaryContent implements Serializable {
    private int id;
    @Serial
    private final static long serialVersionUID = 987654321L;
    private String title;
    private String body;
    private String userEmail;
    private LocalDateTime localDateTime;
}
