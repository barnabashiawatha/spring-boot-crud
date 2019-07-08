package crud.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Getter
    @NonNull
    @Column(name = "username")
    private String username;

    @Setter
    @Getter
    @NonNull
    @Column (name = "password")
    private String password;

    @Setter
    @Getter
    @Column(name = "active")
    private boolean active;

    @Getter
    @Setter
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}