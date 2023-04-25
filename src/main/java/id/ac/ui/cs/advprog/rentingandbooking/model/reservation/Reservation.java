package id.ac.ui.cs.advprog.rentingandbooking.model.reservation;


import com.fasterxml.jackson.annotation.JsonBackReference;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

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
    @JsonBackReference
    private Space space;

    @Column(nullable = false)
    private Integer spacePrice;

    @ManyToOne
    private Invoice invoice;

    //userID
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(nullable = false)
//    private UUID userId;

}
