package pl.projekt.zpwj.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "movie")
    private List<Reservation> reservation;

    private int rankingNumber;

    private String posterLink;

    @NonNull
    private String title;
    @NonNull
    private String originalTitle;
    @NonNull
    private String year;
    @NonNull
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> genres;
    @NonNull
    private double rating;
    @NonNull
    private String director;


}
