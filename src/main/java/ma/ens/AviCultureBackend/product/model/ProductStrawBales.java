package ma.ens.AviCultureBackend.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.model.Building;

@Entity
@Table(name = "product_straw_bales")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductStrawBales extends Product {

    @Column(name = "type")
    private String type;
    @Builder
    public ProductStrawBales(String id, String name, String description, Building storageBuilding, Double unitaryPrice, String type) {
        super(id, name, description, storageBuilding, unitaryPrice);
        this.type = type;
    }
}
