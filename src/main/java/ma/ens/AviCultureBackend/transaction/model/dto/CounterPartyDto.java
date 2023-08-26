package ma.ens.AviCultureBackend.transaction.model.dto;

import jakarta.validation.constraints.Email;
import ma.ens.AviCultureBackend.transaction.model.CounterParty;

import java.util.List;

public record CounterPartyDto(
        Long id,
        @Email(message = "email address does not respect email format")
        String emailAddress,
        String name,
        String address,
        String phoneNumber,
        CounterParty.Type type,
        List<String> suppliesType

) {
}
