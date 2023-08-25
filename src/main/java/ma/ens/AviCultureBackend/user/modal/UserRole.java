package ma.ens.AviCultureBackend.user.modal;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role")
@Getter
@Setter
@Builder
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Role name;

    public enum Role {
        ADMIN , OPERATOR
    }


}
