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
    //Please Make sure that The string Role Matches the enums, And always starts with ROLE_
    public enum Role {

        ROLE_OPERATOR, ROLE_MANAGER, ROLE_DIRECTOR, ROLE_ADMINISTRATOR;
        public static final String ROLE_OPERATOR_VALUE = "ROLE_OPERATOR";

        public static final String ROLE_MANAGER_VALUE = "ROLE_MANAGER";

        public static final String ROLE_DIRECTOR_VALUE = "ROLE_DIRECTOR";

        public static final String ROLE_ADMIN_VALUE = "ROLE_ADMINISTRATOR";

    }


}
