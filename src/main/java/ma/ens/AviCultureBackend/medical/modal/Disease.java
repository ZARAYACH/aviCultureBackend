package ma.ens.AviCultureBackend.medical.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ma.ens.AviCultureBackend.product.model.ProductMedicine;

import java.util.List;

@Entity
@Table(name = "disease")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Disease {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProductMedicine> medicines;

}
