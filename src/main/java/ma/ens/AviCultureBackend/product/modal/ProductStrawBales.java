package ma.ens.AviCultureBackend.product.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;

import java.util.List;

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

    public ProductStrawBales(String id, String name, String description, Building storageBuilding, Double unitaryPrice, Long quantity, List<TransactionProductDetail> transactionProductDetails, String type) {
        super(id, name, description, storageBuilding, unitaryPrice, quantity, transactionProductDetails);
        this.type = type;
    }
}
