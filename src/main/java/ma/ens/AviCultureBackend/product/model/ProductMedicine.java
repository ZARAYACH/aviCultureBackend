package ma.ens.AviCultureBackend.product.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ens.AviCultureBackend.breeding.model.Building;
import ma.ens.AviCultureBackend.medical.modal.Disease;

import java.util.List;

@Entity
@Table(name = "product_chicken")
@Getter
@Setter
@NoArgsConstructor
public class ProductMedicine extends Product {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "medicine_disease",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private List<Disease> diseases;

    @Builder
    public ProductMedicine(String id, String name, String description, Building storageBuilding, Double unitaryPrice, List<Disease> diseases) {
        super(id, name, description, storageBuilding, unitaryPrice);
        this.diseases = diseases;
    }
}
