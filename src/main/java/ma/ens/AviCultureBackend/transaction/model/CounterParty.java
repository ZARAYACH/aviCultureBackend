package ma.ens.AviCultureBackend.transaction.model;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.transaction.converter.SuppliesTypeConverter;

import java.util.List;

@Entity
@Table(name = "transaction_counter_party")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CounterParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_addres", unique = true)
    private String emailAddress;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "supplies_type")
    @Convert(converter = SuppliesTypeConverter.class)
    private List<String> suppliesType;

    public enum Type {
        CUSTOMER, SUPPLIER
    }

}
