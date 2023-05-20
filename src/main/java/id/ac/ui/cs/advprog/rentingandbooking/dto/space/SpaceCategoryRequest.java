package id.ac.ui.cs.advprog.rentingandbooking.dto.space;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceCategoryRequest {
    private String name;
    
    private String type;
}