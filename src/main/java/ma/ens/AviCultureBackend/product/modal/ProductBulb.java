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
@Table(name = "product_bulbs")
@Getter
@Setter
@NoArgsConstructor
public class ProductBulb extends Product {

    @Column(name = "marque")
    private String marque;

    @Column(name = "power")
    private Integer powerInWatt;

    @Builder

    public ProductBulb(String id, String name, String description, Building storageBuilding, Double unitaryPrice, Long quantity, List<TransactionProductDetail> transactionProductDetails, String marque, Integer powerInWatt) {
        super(id, name, description, storageBuilding, unitaryPrice, quantity, transactionProductDetails);
        this.marque = marque;
        this.powerInWatt = powerInWatt;
    }
}
