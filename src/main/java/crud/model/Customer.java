package crud.model;

import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Getter
    @NonNull
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @NonNull
    @Column(name = "age")
    private Integer age;
}