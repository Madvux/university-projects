package pl.projekt.zpwj.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public enum Type {
        USER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    @Column
    private String password;

    @NonNull
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Type role;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}
