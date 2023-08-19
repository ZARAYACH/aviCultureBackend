package ma.ens.AviCultureBackend.breeding.model;

import org.springframework.cglib.core.Block;

import java.util.List;

public record BuildingDto(
        Long id,
        String name,
        Float humidityRate,
        Float surface,

        Float temperature,
        Building.BreedingBuildingState state,
        BreedingCenter breedingCenter,
        List<Block> blocks
) {
}
