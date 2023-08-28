package ma.ens.AviCultureBackend.product.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<TransactionProductDetail> transactionProductDetails;

    public Product(String id, String name, String description, Building storageBuilding, Double unitaryPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.storageBuilding = storageBuilding;
        this.unitaryPrice = unitaryPrice;
    }
}
