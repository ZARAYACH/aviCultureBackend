package ma.ens.AviCultureBackend.incident.modal;

import jakarta.persistence.*;
import lombok.*;
import ma.ens.AviCultureBackend.user.modal.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sanction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sanction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "summary", columnDefinition = "LONGTEXT")
    private String summary;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_Date")
    private String endDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_sanctions",
            joinColumns = @JoinColumn(name = "sanction_id"),
            inverseJoinColumns = @JoinColumn(name ="user_id") )
    private List<User> sanctionedUsers;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
