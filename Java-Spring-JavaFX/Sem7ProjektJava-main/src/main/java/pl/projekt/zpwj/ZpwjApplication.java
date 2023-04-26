package pl.projekt.zpwj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.projekt.zpwj.model.Movie;
import pl.projekt.zpwj.model.Reservation;
import pl.projekt.zpwj.model.User;
import pl.projekt.zpwj.repository.MovieRepository;
import pl.projekt.zpwj.repository.ReservationRepository;
import pl.projekt.zpwj.repository.UserRepository;
import pl.projekt.zpwj.utils.JSoupWebScrapper;
import pl.projekt.zpwj.utils.PasswordHash;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
public class ZpwjApplication extends Application {

    public static ConfigurableApplicationContext springContext;
    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(ZpwjApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/fxml/welcome.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Projekt Java");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }


    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ReservationRepository reservationRepository, MovieRepository movieRepository) {
        return args -> {
            User user = userRepository.findByUsername("admin");

            if (user == null) {
                User admin = new User("admin", PasswordHash.encrypt("admin", "java"), "admin@admin");
                admin.setRole(User.Type.ADMIN);
                userRepository.save(admin);
            }

            List<Movie> movieList = movieRepository.findAll();
            if (movieList.isEmpty()) movieRepository.saveAll(JSoupWebScrapper.initMoviesData());

            user = userRepository.findByUsername("admin");
            List<Reservation> reservationList = reservationRepository.findByUserId(user.getId());
            if (reservationList.isEmpty()) {
                List<Movie> movieList1 = movieRepository.findAll();

                Reservation reservation = new Reservation();
                User user1 = userRepository.findByUsername("admin");
                Movie movie1 = movieRepository.findById(movieList1.get(1).getId()).orElseThrow();
                reservation.setUser(user1);
                reservation.setMovie(movie1);
                Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                reservation.setDate(sqlDate);
                reservation.setTime(Reservation.Time.POLUDNIE);

                Reservation reservation2 = new Reservation();
                Movie movie2 = movieRepository.findById(movieList1.get(2).getId()).orElseThrow();
                reservation2.setUser(user1);
                reservation2.setMovie(movie2);
                reservation2.setDate(sqlDate);
                reservation2.setStatus(true);
                reservation2.setTime(Reservation.Time.RANO);

                reservationRepository.save(reservation);
                reservationRepository.save(reservation2);
            }

        };
    }

}
