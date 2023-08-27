package ma.ens.AviCultureBackend.document.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.vehicle.model.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    private DocumentType type;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "inserted_at")
    private LocalDateTime inserterAt;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "documents")
    @JoinTable(name = "vehicle_documents",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    private List<Vehicle> vehicles;
}
