package ma.ens.AviCultureBackend.incident.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.user.modal.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "summary", columnDefinition = "LONGTEXT")
    private String summary;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private User reporter;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "reported_at")
    private final LocalDateTime reportedAt = LocalDateTime.now();



}
