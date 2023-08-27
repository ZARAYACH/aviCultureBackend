package ma.ens.AviCultureBackend.vehicle.model;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.document.modal.Document;
import ma.ens.AviCultureBackend.user.modal.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "marque")
    private String marque;

    @Column(name = "module")
    private Integer module;

    @Column(name = "licence_plate")
    private String licencePlate;

    @Column(name = "first_rolling_date")
    private LocalDate firstRollingDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "vehicles")
    @JoinTable(name = "vehicle_drivers",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> drivers;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<Document> documents;
}
