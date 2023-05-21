package id.ac.ui.cs.advprog.rentingandbooking.service.reservation;

import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;

import java.util.List;

public interface ReservationService {
    String create(ReservationRequest request);

    //    Reservation findById();
    List<ReservationResponse> findAll();

//    TODO: findbyuser
}
