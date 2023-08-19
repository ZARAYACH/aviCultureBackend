package ma.ens.AviCultureBackend.breeding.model;

import jakarta.persistence.*;
import lombok.*;

//lot
@Entity
@Table(name = "breeding_block")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "daily_mortality")
    private Integer dailyMortality;

    @Column(name = "daily_gas_cylinder")
    private Float dailyGasCylinder;

    @Column(name = "weight_fisrt_week")
    private Float weightFirstWeek;

    @Column(name = "weight_every_Feeding")
    private Float weightEveryFeeding;

    @Column(name = "weight_by_end")
    private Float weightByTheEnd;

    @Column(name = "food_nature")
    private String foodNature;

    @Column(name = "food_quantity")
    private String foodQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;
}
