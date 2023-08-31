package ma.ens.AviCultureBackend.document.modal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DocumentTypeDto(
        Long id,
        String name
){
}
