package data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContentRequest {
    private int id;
    private String title;
    private String body;
}
