package ma.ens.AviCultureBackend.medical.modal.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import ma.ens.AviCultureBackend.product.modal.ProductMedicine;
import ma.ens.AviCultureBackend.task.modal.VaccinationTask;

import java.util.ArrayList;
import java.util.List;

public record DiseaseDto(
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,
        List<String> medicineIds
) {
}
