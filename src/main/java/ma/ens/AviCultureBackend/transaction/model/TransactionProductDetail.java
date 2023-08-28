package ma.ens.AviCultureBackend.transaction.model;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.product.modal.Product;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_product_detail")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Transaction transaction;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;

}
