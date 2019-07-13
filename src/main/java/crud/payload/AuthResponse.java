package crud.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class AuthResponse {

    @NonNull
    @Getter
    @Setter
    private String accessToken;

    @Getter
    private String tokenType = "Bearer";
}
