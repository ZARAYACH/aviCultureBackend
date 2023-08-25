package ma.ens.AviCultureBackend.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tool_categorie")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ToolCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "toolCategorie")
    private List<ProductTool> tools;
}
