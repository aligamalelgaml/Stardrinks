package fastrack.stardrinks.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
