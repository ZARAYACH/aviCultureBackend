package ma.ens.AviCultureBackend.breeding.model;

import java.util.List;

public record BreedingCenterDto(
        Long id,
        String name,
        String address,
        List<Building> buildings
) {
}
