package id.ac.ui.cs.advprog.rentingandbooking.dto.reservation;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer spaceId;
    private String spaceName;
    private Integer spacePrice;
    private Date rentDate;

}
