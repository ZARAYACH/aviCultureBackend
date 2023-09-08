package ma.ens.AviCultureBackend.product.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "food_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
