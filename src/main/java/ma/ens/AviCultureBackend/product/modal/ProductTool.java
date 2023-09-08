package ma.ens.AviCultureBackend.product.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;

import java.util.List;

@Entity
@Table(name = "product_tool")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductTool extends Product {

    @Column(name = "usedFor")
    private String usedFor;

    @ManyToOne(fetch = FetchType.EAGER)
    private ToolCategorie toolCategorie;

    @Builder
    public ProductTool(String id, String name, String description, Building storageBuilding, Double unitaryPrice, Long quantity, List<TransactionProductDetail> transactionProductDetails, String usedFor, ToolCategorie toolCategorie) {
        super(id, name, description, storageBuilding, unitaryPrice, quantity, transactionProductDetails);
        this.usedFor = usedFor;
        this.toolCategorie = toolCategorie;
    }
}
