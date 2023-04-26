package pl.projekt.zpwj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.zpwj.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
