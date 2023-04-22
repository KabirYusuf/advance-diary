package data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {
    @Serial
    private final static long serialVersionUID = 123456789L;
    private String email;
    private String password;
}
