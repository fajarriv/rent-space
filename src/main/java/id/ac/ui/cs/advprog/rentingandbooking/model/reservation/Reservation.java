package id.ac.ui.cs.advprog.rentingandbooking.model.reservation;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", nullable = false)
    @JsonManagedReference
    private Space space;

    @Column(nullable = false)
    private Boolean isPaid;

//TODO: Column for user who reserve

}
