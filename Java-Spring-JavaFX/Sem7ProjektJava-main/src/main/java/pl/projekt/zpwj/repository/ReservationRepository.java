package pl.projekt.zpwj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.projekt.zpwj.model.Reservation;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long id);
    List<Reservation> findByDateAndStatus(Date date,boolean status);
}
