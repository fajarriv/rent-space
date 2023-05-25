package id.ac.ui.cs.advprog.rentingandbooking.controller.reservation;

import id.ac.ui.cs.advprog.rentingandbooking.dto.Temporary;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationPayment;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationResponse;
import id.ac.ui.cs.advprog.rentingandbooking.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ROOM_RENTER')")
    @PostMapping("")
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request) {
//        handle empty request
        String response;
        response = reservationService.create(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<ReservationResponse>> getAllReservation() {
        List<ReservationResponse> response;
        response = reservationService.findAll();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_ROOM_RENTER')")
    @GetMapping("/unpaid")
    public ResponseEntity<List<ReservationResponse>> getUnpaidReservations(@RequestBody Temporary request) {
        List<ReservationResponse> response;
        response = reservationService.findUnpaidReservations(request.getCurrentUser());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_ROOM_RENTER')")
    @PatchMapping("/pay")
    public ResponseEntity<List<ReservationResponse>> payReservation(@RequestBody ReservationPayment request) {
        List<ReservationResponse> response;
        response = reservationService.updatePaymentStatus(request);
        return ResponseEntity.ok(response);
    }
}
