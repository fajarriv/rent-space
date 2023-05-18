package id.ac.ui.cs.advprog.rentingandbooking.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRequest {
    private String name;

    private String date;

    private Integer price;

    private Integer capacity;

    private String description;
    private String categoryName;
    private String type;
}
