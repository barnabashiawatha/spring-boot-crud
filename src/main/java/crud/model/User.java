package crud.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Email
    @Column(nullable = false)
    private String email;

    @Setter
    @Getter
    @JsonIgnore
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Getter
    @Setter
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Getter
    @Setter
    private String providerId;
}