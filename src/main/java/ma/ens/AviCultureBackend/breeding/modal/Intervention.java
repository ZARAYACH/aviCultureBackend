package ma.ens.AviCultureBackend.breeding.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "intervention")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nature")
    @Enumerated(EnumType.STRING)
    private Nature nature;

    @Column(name = "beginning_date")
    private LocalDateTime beginningDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;

    @Getter
    public enum Nature {
        CLEANING("travaux de Nettoyage"), MAINTENANCE("Maintenance");
        private final String lable;

        Nature(String lable) {
            this.lable = lable;
        }
    }
}
