package pl.projekt.zpwj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.projekt.zpwj.model.Movie;
import pl.projekt.zpwj.utils.JSoupWebScrapper;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void givenListOfMovies_whenGetMovies_thenCount25Records(){
        //given
        movieRepository.saveAll(JSoupWebScrapper.initMoviesData());

        //when
        List<Movie> movieList = movieRepository.findAll();

        assertEquals(25, movieList.size());
    }
}