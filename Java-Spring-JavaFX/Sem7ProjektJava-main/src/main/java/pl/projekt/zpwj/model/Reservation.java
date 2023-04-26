package pl.projekt.zpwj.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {

    public enum Time {
        RANO,
        POLUDNIE,
        WIECZOR,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Date date;


    @Enumerated(EnumType.STRING)
    private Time time;

    @NonNull
    private boolean status = false;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
