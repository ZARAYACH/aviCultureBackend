package ma.ens.AviCultureBackend.product.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.modal.Block;
import ma.ens.AviCultureBackend.breeding.modal.Building;

@Entity
@Table(name = "product_chicken")
@Getter
@Setter
@NoArgsConstructor
public class ProductChicken extends Product{

    @ManyToOne(fetch = FetchType.EAGER)
    private Block block;

    @Builder
    public ProductChicken(String id, String name, String description, Building storageBuilding, Double unitaryPrice, Block block) {
        super(id, name, description, storageBuilding, unitaryPrice);
        this.block = block;
    }

}
