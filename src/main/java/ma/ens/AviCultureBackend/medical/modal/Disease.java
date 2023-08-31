package ma.ens.AviCultureBackend.medical.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import ma.ens.AviCultureBackend.task.modal.VaccinationTask;

import java.util.List;

@Entity
@Table(name = "disease")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Disease {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProductMedicine> medicines;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "disease")
    private List<VaccinationTask> vaccinationTasks;

}
