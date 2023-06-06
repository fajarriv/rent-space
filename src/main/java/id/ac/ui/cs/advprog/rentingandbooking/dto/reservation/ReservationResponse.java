package id.ac.ui.cs.advprog.rentingandbooking.dto.reservation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Integer id;
    private String email;
    private Boolean isPaid;
    private Integer spaceId;
    private String spaceName;
    private Integer spacePrice;
    private Date rentDate;
}
