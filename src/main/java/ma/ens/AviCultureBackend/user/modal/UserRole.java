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
    //Please Make sure that The string Role Matches the enums
    public enum Role {

        ROLE_ADMIN, ROLE_OPERATOR;
        public static final String ROLE_OPERATOR_VALUE = "ROLE_OPERATOR";
        public static final String ROLE_ADMIN_VALUE = "ROLE_ADMIN";

    }


}
