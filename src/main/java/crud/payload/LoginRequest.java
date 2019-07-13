package crud.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
public class LoginRequest {

    @Getter
    @Setter
    @NotBlank
    @Email
    private String email;

    @Getter
    @Setter
    @NotBlank
    private String password;
}
