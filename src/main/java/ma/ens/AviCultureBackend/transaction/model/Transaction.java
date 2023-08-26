package ma.ens.AviCultureBackend.transaction.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    private CounterParty counterParty;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction" )
    private List<TransactionProductDetail> transactionProductsDetails;

    @Column(name = "time_Stamp")
    private LocalDateTime timeStamp = LocalDateTime.now();

    public enum Type {
        PURCHASE, SELES
    }

}
