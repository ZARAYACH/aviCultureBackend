package ma.ens.AviCultureBackend.product.modal;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ens.AviCultureBackend.breeding.modal.Building;

@Entity
@Table(name = "product_food")
@Getter
@Setter
@NoArgsConstructor
public class ProductFood extends Product {

    @ManyToOne(fetch = FetchType.EAGER)
    private FoodCategory productFoodCategory;

    @Column(name = "Remarks", columnDefinition = "LONGTEXT")
    private String Remarks;

    @Builder
    public ProductFood(String id, String name, String description, Building storageBuilding, Double unitaryPrice, FoodCategory productFoodCategory, String remarks) {
        super(id, name, description, storageBuilding, unitaryPrice);
        this.productFoodCategory = productFoodCategory;
        Remarks = remarks;
    }
}
