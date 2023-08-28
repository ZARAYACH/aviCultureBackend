package ma.ens.AviCultureBackend.task.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.modal.Block;
import ma.ens.AviCultureBackend.medical.modal.Disease;

import java.time.LocalDateTime;

@Entity
@Table(name = "medication_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Block block;

    @ManyToOne(fetch = FetchType.EAGER)
    private Disease disease;

    @Column(name = "date")
    private LocalDateTime date;

}
