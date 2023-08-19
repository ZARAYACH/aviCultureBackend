package ma.ens.AviCultureBackend.breeding.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "breeding_building")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "humidity_rate")
    private Float humidityRate;

    @Column(name = "surface")
    private Float surface;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private BreedingBuildingState state;

    @ManyToOne(fetch = FetchType.EAGER)
    private BreedingCenter breedingCenter;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
    private List<Block> blocks;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<Intervention> interventions;

    @Getter
    public enum BreedingBuildingState {
        Free("Libre"), Busy("occupé"), Cycle_Phase_breeding("Phase du cycle d’élevage");
        private final String label;

        BreedingBuildingState(String label) {
            this.label = label;
        }
    }
}
