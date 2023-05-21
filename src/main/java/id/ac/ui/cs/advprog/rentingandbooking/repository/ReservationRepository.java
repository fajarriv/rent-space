package id.ac.ui.cs.advprog.rentingandbooking.repository;

import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @NonNull
    List<Reservation> findAll();


    List<Reservation>findAllByEmail(@NonNull String email);

    @NonNull
    Optional<Reservation> findById(@NonNull Integer id);
}
