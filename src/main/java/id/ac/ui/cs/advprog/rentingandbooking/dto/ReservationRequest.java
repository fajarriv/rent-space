package id.ac.ui.cs.advprog.rentingandbooking.dto;

import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private Space space;
    private String date;

    private int duration;

    private String paymentMethod;
}