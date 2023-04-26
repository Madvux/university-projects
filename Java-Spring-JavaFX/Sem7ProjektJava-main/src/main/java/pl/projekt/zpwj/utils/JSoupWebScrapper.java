package pl.projekt.zpwj.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.projekt.zpwj.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSoupWebScrapper {
    //fetching first 25 records from website using Jsoup
    public static List<Movie> initMoviesData() {
        List<Movie> movies = new ArrayList<>();

        String url = "https://www.filmweb.pl/ranking/film";
        try {

            Document doc = Jsoup.connect(url).timeout(6000).get();
            Elements allMovies = doc.select("div.page__container.rankingTypeSection__container");

            for (Element e : allMovies.select("div.rankingType")) {
                Movie movie = new Movie();
                movie.setRankingNumber(Integer.parseInt(e.select("div.rankingType > span").text()));
                movie.setPosterLink(e.select("div.efficientPoster > span").text());
                movie.setTitle(e.select("div.rankingType__titleWrapper").text());
                movie.setOriginalTitle(e.select("p.rankingType__originalTitle").first().ownText());
                movie.setYear(e.select("p.rankingType__originalTitle > span").text());
                movie.setGenres(e.select("div.rankingType__genres a").eachText());
                movie.setRating(Double.parseDouble(e.select("div.rankingType__rate").select("span.rankingType__rate--value").text().replaceAll(",", ".")));
                movie.setDirector(e.select("div a span").text());
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
