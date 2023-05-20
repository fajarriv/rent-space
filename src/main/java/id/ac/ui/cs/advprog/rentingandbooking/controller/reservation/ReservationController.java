package id.ac.ui.cs.advprog.rentingandbooking.controller.reservation;

import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationRequest;

import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request) {
//        handle empty request
        String response;
        response = reservationService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<ReservationResponse>> getAllReservation() {
        List<ReservationResponse> response;
        response = reservationService.findAll();
        return ResponseEntity.ok(response);
    }
}
