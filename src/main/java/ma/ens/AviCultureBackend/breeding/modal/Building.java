package ma.ens.AviCultureBackend.breeding.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.product.modal.Product;
import ma.ens.AviCultureBackend.task.modal.BulbsReplacementTask;

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

    @Column(name = "nature")
    @Enumerated(EnumType.STRING)
    private Nature nature;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building", cascade = CascadeType.ALL)
    private List<Block> blocks;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Intervention> interventions;

    @OneToMany(mappedBy = "storageBuilding", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<BulbsReplacementTask> bulbsReplacementTasks;


    @Getter
    public enum BreedingBuildingState {
        Free("Libre"), Busy("occupé"), Cycle_Phase_breeding("Phase du cycle d’élevage");
        private final String label;

        BreedingBuildingState(String label) {
            this.label = label;
        }
    }

    @Getter
    public enum Nature {
        BREEDING, STORAGE;
    }
}
