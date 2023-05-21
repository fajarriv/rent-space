package id.ac.ui.cs.advprog.rentingandbooking.service.reservation;

import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationPayment;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationResponse;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.reservation.ReservationIdDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceDoesNotAvailableException;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.repository.ReservationRepository;
import id.ac.ui.cs.advprog.rentingandbooking.service.space.SpaceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final SpaceServiceImpl spaceService;
    private final ReservationRepository reservationRepository;

    @Override
    public List<ReservationResponse> findAll() {
        return convertToResponse(reservationRepository.findAll());
    }

    @Override
    public List<Reservation> findAllByEmail(String email) {
        return reservationRepository.findAllByEmail(email);
    }

    @Override
    public List<ReservationResponse> findUnpaidReservations(String email) {
        List<Reservation> reservations = this.findAllByEmail(email);
        List<Reservation> unpaidReservations = reservations.stream().filter(reservation -> !reservation.getIsPaid()).toList();
        return convertToResponse(unpaidReservations);
    }

    @Override
    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationIdDoesNotExistException(id));
    }

    @Override
    public String create(ReservationRequest request) {
        List<Space> spaces = request.getSpaceId().stream().map(spaceService::findById).toList();
        spaces.forEach(space -> {
            Reservation newReservation = Reservation.builder()
                    .email(request.getEmail())
                    .space(space)
                    .isPaid(false)
                    .build();
            if (!isSpaceAvailable(space.getId())) {
                throw new SpaceDoesNotAvailableException(space);
            }

            reservationRepository.save(newReservation);
            spaceService.updateAvailibility(space.getId(), space.getIsAvailable());
        });
        return "Reservation created successfully!";
    }


    @Override
    public List<ReservationResponse> updatePaymentStatus(ReservationPayment request) {
        List<Reservation> reservations = request.getReservationId().stream().map(this::findById).toList();
        reservations.forEach(reservation -> {
            reservation.setIsPaid(true);
            reservationRepository.save(reservation);
        });
        return convertToResponse(reservations);
    }

    private List<ReservationResponse> convertToResponse(List<Reservation> reservations) {
        return reservations.stream().map(reservation -> ReservationResponse.builder()
                .id(reservation.getId())
                .email(reservation.getEmail())
                .isPaid(reservation.getIsPaid())
                .spaceId(reservation.getSpace().getId())
                .spaceName(reservation.getSpace().getName())
                .spacePrice(reservation.getSpace().getPrice())
                .rentDate(reservation.getSpace().getDate())
                .build()).toList();
    }

    private boolean isSpaceAvailable(Integer spaceId) {
        return spaceService.findById(spaceId).getIsAvailable();
    }
}
