package data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDiaryContentRequest {
    private String userEmail;
    private String title;
    private String body;
}
