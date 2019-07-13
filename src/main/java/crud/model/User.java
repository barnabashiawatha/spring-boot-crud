package crud.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

//@RequiredArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Getter
//    @NonNull
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Email
    @Column(nullable = false)
    private String email;

    @Setter
    @Getter
//    @NonNull
    @JsonIgnore
    private String password;

    @Getter
    @Setter
    private Boolean emailVerified = false;

//    @Setter
//    @Getter
//    @Column(name = "active")
//    private boolean active;

//    @Getter
//    @Setter
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;

    @Getter
    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Getter
    @Setter
    private String providerId;
}