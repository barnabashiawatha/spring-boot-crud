package crud.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerUI {

    @Getter
    private long id;

    @Getter
    @NonNull
    private String name;

    @Getter
    @NonNull
    private Integer age;
}