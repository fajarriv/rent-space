package id.ac.ui.cs.advprog.rentingandbooking.repository;

import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @NonNull
    List<Reservation> findAll();
}
