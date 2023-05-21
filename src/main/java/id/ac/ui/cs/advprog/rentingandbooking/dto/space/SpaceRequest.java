package id.ac.ui.cs.advprog.rentingandbooking.dto.space;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRequest {
    private String name;
    private String owner;
    private List<Date> date;

    private Integer price;

    private Integer capacity;

    private String description;
    private String categoryName;
    private List<String> facilities;

}
