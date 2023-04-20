package id.ac.ui.cs.advprog.rentingandbooking.model.space;

import com.fasterxml.jackson.annotation.JsonBackReference;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "space_types")
public class SpaceType {
    @Id
    @Column(nullable = false)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "spaceType")
    private List<Space> spaces;
}
