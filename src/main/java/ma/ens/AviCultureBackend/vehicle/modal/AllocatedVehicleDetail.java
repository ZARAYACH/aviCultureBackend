package ma.ens.AviCultureBackend.vehicle.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Allocated_vehicle_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocatedVehicleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason", columnDefinition = "LONGTEXT")
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    private BreedingCenter breedingCenter;

    @Column(name = "allocation_date")
    private LocalDateTime allocationDate;

    @Column(name = "deAllocation_date")
    private LocalDateTime deAllocationDate;

    @Column(name = "added_at")
    private final LocalDateTime addedAt = LocalDateTime.now();
}
