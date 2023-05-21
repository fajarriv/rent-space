package id.ac.ui.cs.advprog.rentingandbooking.dto.reservation;

import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private List<Integer> spaceId;
    private String email;
//    TODO: Add user
}