package ma.ens.AviCultureBackend.product.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;

import java.util.List;

@Entity
@Table(name = "product_gas_cylinder")
@Getter
@Setter
@NoArgsConstructor
public class ProductGasCylinder extends Product {

    @Column(name = "type")
    private String type;

    @Builder

    public ProductGasCylinder(String id, String name, String description, Building storageBuilding, Double unitaryPrice, Long quantity, List<TransactionProductDetail> transactionProductDetails, String type) {
        super(id, name, description, storageBuilding, unitaryPrice, quantity, transactionProductDetails);
        this.type = type;
    }
}
