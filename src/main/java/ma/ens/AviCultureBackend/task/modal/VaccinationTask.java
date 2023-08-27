package ma.ens.AviCultureBackend.task.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.model.Block;
import ma.ens.AviCultureBackend.medical.modal.Disease;

import java.time.LocalDateTime;

@Entity
@Table(name = "vaccination_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Block block;

    @ManyToOne(fetch = FetchType.EAGER)
    private Disease disease;

    @Column(name = "date")
    private LocalDateTime date;

    public enum Type {
        PRINCIPAL, RAPPEL
    }


}
