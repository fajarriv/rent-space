package id.ac.ui.cs.advprog.rentingandbooking.model.reservation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(nullable = false, columnDefinition = "INT CHECK (price > 0)")
    private Integer totalPrice;

    @Column(nullable = false)
    private Boolean isCompleted;

    @Column(nullable = false)
    private String paymentMethod;
}
