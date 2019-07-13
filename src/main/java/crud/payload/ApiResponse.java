package crud.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ApiResponse {

    @Getter
    @Setter
    private boolean success;

    @Getter
    @Setter
    private String message;
}
