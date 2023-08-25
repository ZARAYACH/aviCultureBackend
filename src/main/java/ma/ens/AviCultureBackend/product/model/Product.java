package ma.ens.AviCultureBackend.product.model;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.model.Building;

@Entity
@Table(name = "product")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building storageBuilding;

    @Column(name = "unitary_price")
    private Double unitaryPrice;

}
