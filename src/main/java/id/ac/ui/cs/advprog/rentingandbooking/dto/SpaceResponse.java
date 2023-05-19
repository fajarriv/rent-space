package id.ac.ui.cs.advprog.rentingandbooking.dto;

import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResponse {
    private String name;
    private Integer price;
    private Integer capacity;
    private String description;
    private SpaceCategory category;

    public SpaceResponse(String name, String description, SpaceCategory category, Integer capacity, Integer price) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.category = category;
        this.price = price;
    }
}
