package id.ac.ui.cs.advprog.rentingandbooking.dto.space;

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
    private String owner;
    private Integer price;
    private Integer capacity;
    private String description;
    private SpaceCategory category;
    public SpaceResponse(String name,String owner, String description, SpaceCategory category, Integer capacity, Integer price) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.capacity = capacity;
        this.category = category;
        this.price = price;
    }
}
