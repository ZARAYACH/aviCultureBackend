package ma.ens.AviCultureBackend.transaction.mapper;


import ma.ens.AviCultureBackend.transaction.model.CounterParty;
import ma.ens.AviCultureBackend.transaction.model.dto.CounterPartyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CounterPartyMapper {
	CounterPartyDto toCounterPartyDto(CounterParty counterParty);

	@Mapping(source = "product.id", target = "productId")
	List<CounterPartyDto> toCounterPartyDtos(List<CounterParty> counterParties);

}
