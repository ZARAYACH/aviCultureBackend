package ma.ens.AviCultureBackend.breeding.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "breeding_center")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreedingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "breedingCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Building> buildings;
}
