package id.ac.ui.cs.advprog.rentingandbooking.model.space;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import jakarta.persistence.*;
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
@Entity
@Table(name = "spaces", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "date"})})
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false, columnDefinition = "INT CHECK (price > 0)")
    private Integer price;
    @Column(nullable = false, columnDefinition = "INT CHECK (capacity > 0)")
    private Integer capacity;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Boolean isAvailable;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpaceStatus status;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpaceCategory category;

    @OneToOne(mappedBy = "space", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Reservation reservation;

    @ElementCollection
    private List<String> facilities;

    //TODO: Layanan opsional
//    @ElementCollection
//    private List<String> facilities;

//TODO: Column for user (owner)
//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "_user_id", nullable = false)
//    private User user;
}
