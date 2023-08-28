package ma.ens.AviCultureBackend.vehicle.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_intervention")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleIntervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nature")
    private String nature;

    @Column(name = "description")
    private String description;

    @Column(name = "kilometerage")
    private Integer kilometerage;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "price")
    private Float price;

    @Column(name = "mechanic_name")
    private String mechanicName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;
}
