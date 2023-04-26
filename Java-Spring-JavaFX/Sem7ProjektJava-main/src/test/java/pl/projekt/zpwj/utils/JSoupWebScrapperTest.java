package pl.projekt.zpwj.utils;

import org.junit.jupiter.api.Test;
import pl.projekt.zpwj.model.Movie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSoupWebScrapperTest {

    @Test
    void givenInternetConnection_whenOK_thenReturn25Movies() throws Exception {
        List<Movie> movieList= JSoupWebScrapper.initMoviesData();

        assertEquals(movieList.get(1).getTitle(), "Nietykalni");

        assertEquals(25, movieList.size());
    }
}