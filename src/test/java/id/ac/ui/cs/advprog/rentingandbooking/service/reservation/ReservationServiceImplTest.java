package id.ac.ui.cs.advprog.rentingandbooking.service.reservation;

import id.ac.ui.cs.advprog.rentingandbooking.Util;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationPayment;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationResponse;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.reservation.ReservationIdDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceDoesNotAvailableException;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceStatus;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import id.ac.ui.cs.advprog.rentingandbooking.repository.ReservationRepository;
import id.ac.ui.cs.advprog.rentingandbooking.service.space.SpaceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @InjectMocks
    ReservationServiceImpl reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    SpaceServiceImpl spaceService;

    Space space0;
    Space space1;
    Reservation reservation0;
    Reservation reservation1;

    ReservationRequest reservationRequest;

    ReservationPayment reservationPayment;

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

    @BeforeEach
    void setUp() {
        SpaceCategory spaceCategory = SpaceCategory.builder()
                .name("Villa")
                .type(SpaceType.CLOSED_SPACE)
                .build();

        space0 = Space.builder()
                .id(0)
                .name("Villa ubud")
                .owner("Rayhan")
                .description("Villa dengan pemandangan indah dekat pantai")
                .date(Util.parseDate("2023-03-03"))
                .category(spaceCategory)
                .price(1000000)
                .capacity(25)
                .isAvailable(true)
                .status(SpaceStatus.ON_REVIEW)
                .facilities(Arrays.asList("Wifi", "AC", "TV", "Kamar Mandi", "Dapur"))
                .build();

        space1 = Space.builder()
                .id(1)
                .name("Villa ubud")
                .owner("Rayhan")
                .description("Villa dengan pemandangan indah dekat pantai")
                .date(Util.parseDate("2023-03-04"))
                .category(spaceCategory)
                .price(1000000)
                .capacity(25)
                .isAvailable(true)
                .status(SpaceStatus.ON_REVIEW)
                .facilities(Arrays.asList("Wifi", "AC", "TV", "Kamar Mandi", "Dapur"))
                .build();

        reservation0 = Reservation.builder()
                .id(0)
                .email("Penyewa1@gmail.com")
                .space(space0)
                .isPaid(false)
                .build();

        reservation1 = Reservation.builder()
                .id(1)
                .email("Penyewa1@gmail.com")
                .space(space1)
                .isPaid(false)
                .build();

        reservationRequest = ReservationRequest.builder().spaceId(List.of(0, 1))
                .email("Penyewa1@gmail.com")
                .build();
        reservationPayment = ReservationPayment.builder()
                .reservationId(List.of(0))
                .email("Penyewa1@gmail.com")
                .build();
    }

    @Test
    void whenFindAllShouldReturnListOfReservationResponse() {
        List<ReservationResponse> expectedResponse = convertToResponse(Arrays.asList(reservation0, reservation1));
        when(reservationRepository.findAll()).thenReturn(List.of(reservation0, reservation1));

        List<ReservationResponse> result = reservationService.findAll();
        verify(reservationRepository, atLeastOnce()).findAll();
        assertEquals(expectedResponse, result);
    }

    @Test
    void whenFindAllByEmailShouldReturnListOfReservation() {
        List<Reservation> expectedResponse = Arrays.asList(reservation0, reservation1);
        when(reservationRepository.findAllByEmail(any(String.class))).thenReturn(List.of(reservation0, reservation1));

        List<Reservation> result = reservationService.findAllByEmail("Penyewa1@gmail.com");
        verify(reservationRepository, atLeastOnce()).findAllByEmail(any(String.class));
        assertEquals(expectedResponse, result);
    }

    @Test
    void whenFindUnpaidReservationsShouldReturnListOfResponse() {
        reservation0.setIsPaid(true);
        List<ReservationResponse> expectedResponse = convertToResponse(Collections.singletonList(reservation1));
        when(reservationRepository.findAllByEmail(any(String.class))).thenReturn(List.of(reservation0, reservation1));

        List<ReservationResponse> result = reservationService.findUnpaidReservations("Penyewa1@gmail.com");
        verify(reservationRepository, atLeastOnce()).findAllByEmail(any(String.class));
        assertEquals(expectedResponse, result);
    }

    @Test
    void whenFindByIdAndFoundShouldReturnTheReservation() {
        when(reservationRepository.findById(any(Integer.class))).thenReturn(Optional.of(reservation0));

        Reservation result = reservationService.findById(0);
        verify(reservationRepository, atLeastOnce()).findById(any(Integer.class));
        assertEquals(reservation0, result);
    }

    @Test
    void whenFindByIdAndNotFoundShouldThrowExceptions() {
        when(reservationRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(ReservationIdDoesNotExistException.class, () -> {
            reservationService.findById(0);
        });
    }

    @Test
    void whenCreateReservationShouldReturnSuccessMessage() {
        when(spaceService.findById(any(Integer.class))).thenReturn(space0).thenReturn(space1);

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation0).thenReturn(reservation1);
        String result = reservationService.create(reservationRequest);

        verify(reservationRepository, times(2)).save(any(Reservation.class));
        assertEquals("Reservation created successfully!", result);
    }

    @Test
    void whenCreateReservationButSpaceIsNotAvailableShouldThrowException() {
        space0.setIsAvailable(false);
        space1.setIsAvailable(false);
        when(spaceService.findById(any(Integer.class))).thenReturn(space0).thenReturn(space1);

        Assertions.assertThrows(SpaceDoesNotAvailableException.class, () -> {
            reservationService.create(reservationRequest);
        });
    }
    @Test
    void whenUpdatePaymentStatusShouldReturnListOfResponse(){
        when(reservationRepository.findById(any(Integer.class))).thenReturn(Optional.of(reservation0));
        reservation0.setIsPaid(true);
        List<ReservationResponse> expectedResponse = convertToResponse(Collections.singletonList(reservation0));
        List<ReservationResponse> result = reservationService.updatePaymentStatus(reservationPayment);

        verify(reservationRepository, atLeastOnce()).findById(any(Integer.class));
        verify(reservationRepository, atLeastOnce()).save(any(Reservation.class));
        assertEquals(expectedResponse, result);
    }
}
