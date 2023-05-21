package id.ac.ui.cs.advprog.rentingandbooking.exceptions.reservation;

public class ReservationIdDoesNotExistException extends RuntimeException{

        public ReservationIdDoesNotExistException(Integer id) {
            super("Reservation with id " + id + " does not exist");
        }
}
