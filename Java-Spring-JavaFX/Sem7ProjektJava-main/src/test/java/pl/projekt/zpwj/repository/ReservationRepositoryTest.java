package pl.projekt.zpwj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.projekt.zpwj.model.Movie;
import pl.projekt.zpwj.model.Reservation;
import pl.projekt.zpwj.model.User;
import pl.projekt.zpwj.utils.JSoupWebScrapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    void givenUserId_whenFound_thenReturnListOfReservations() {

        //given
        User admin = new User("admin1", "admin1", "admin1@admin");
        admin.setRole(User.Type.ADMIN);
        userRepository.save(admin);

        movieRepository.saveAll(JSoupWebScrapper.initMoviesData());
        List<Movie> movieList = movieRepository.findAll();

        Reservation reservation = new Reservation();
        User user = userRepository.findByUsername("admin1");
        Movie movie1 = movieRepository.findById(movieList.get(1).getId()).orElseThrow();
        reservation.setUser(user);
        reservation.setMovie(movie1);
        Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        reservation.setDate(sqlDate);
        reservation.setTime(Reservation.Time.POLUDNIE);

        Reservation reservation2 = new Reservation();
        Movie movie2 = movieRepository.findById(movieList.get(2).getId()).orElseThrow();
        reservation2.setUser(user);
        reservation2.setMovie(movie2);
        reservation2.setDate(sqlDate);
        reservation2.setStatus(true);
        reservation2.setTime(Reservation.Time.RANO);

        reservationRepository.save(reservation);
        reservationRepository.save(reservation2);

        //when
        List<Reservation> reservationList = reservationRepository.findByUserId(user.getId());
        List<Reservation> expectedList = new ArrayList<>();
        expectedList.add(reservation);
        expectedList.add(reservation2);
        //then
        assertTrue(!reservationList.isEmpty());

        assertEquals(reservationList.get(0).getDate().toLocalDate(),expectedList.get(0).getDate().toLocalDate());
        assertEquals(reservationList.get(1).getDate().toLocalDate(),expectedList.get(1).getDate().toLocalDate());

        assertEquals(reservationList.get(0).getTime(),expectedList.get(0).getTime());
        assertEquals(reservationList.get(1).getTime(),expectedList.get(1).getTime());

        assertEquals(reservationList.get(0).isStatus(),expectedList.get(0).isStatus());
        assertEquals(reservationList.get(1).isStatus(),expectedList.get(1).isStatus());
    }

    @Test
    void givenDateAndStatus_whenFound_ReturnListOfReservations() {
        //given
        User admin = new User("admin2", "admin2", "admin2@admin");
        admin.setRole(User.Type.ADMIN);
        userRepository.save(admin);

        movieRepository.saveAll(JSoupWebScrapper.initMoviesData());
        List<Movie> movieList = movieRepository.findAll();

        Reservation reservation = new Reservation();
        User user = userRepository.findByUsername("admin2");
        Movie movie1 = movieRepository.findById(movieList.get(1).getId()).orElseThrow();
        reservation.setUser(user);
        reservation.setMovie(movie1);
        Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        reservation.setDate(sqlDate);
        reservation.setTime(Reservation.Time.POLUDNIE);

        Reservation reservation2 = new Reservation();
        Movie movie2 = movieRepository.findById(movieList.get(2).getId()).orElseThrow();
        reservation2.setUser(user);
        reservation2.setMovie(movie2);
        reservation2.setDate(sqlDate);
        reservation2.setStatus(true);
        reservation2.setTime(Reservation.Time.RANO);

        reservationRepository.save(reservation);
        reservationRepository.save(reservation2);

        //when
        List<Reservation> reservationList = reservationRepository.findByDateAndStatus(sqlDate,true);

        //then - expected 1
        assertEquals(1,reservationList.size());
    }
}