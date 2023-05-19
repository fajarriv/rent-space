package id.ac.ui.cs.advprog.rentingandbooking.model.space;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "space_categories")
public class SpaceCategory {
    @Id
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private SpaceType type;

    @JsonBackReference
    @OneToMany(mappedBy = "category")
    private List<Space> spaces;
}
