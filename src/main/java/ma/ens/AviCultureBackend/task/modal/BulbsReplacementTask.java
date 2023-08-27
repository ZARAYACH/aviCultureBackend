package ma.ens.AviCultureBackend.task.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.model.Building;

import java.time.LocalDateTime;

@Entity
@Table(name = "bulbs_replacement_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulbsReplacementTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;

    @Column(name = "replaced_bulb_count")
    private Long replacedBulbCount;

    @Column(name = "date")
    private LocalDateTime date;


}
